package com.fs.fashion_go.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.BannerPagerAdapter;
import com.fs.fashion_go.adapter.RecommendationAdapter;
import com.fs.fashion_go.dao.BaseFragment;
import com.fs.fashion_go.entitys.Banner;
import com.fs.fashion_go.entitys.GetRecommendation;
import com.fs.fashion_go.entitys.ThreeGoodsInfo;
import com.fs.fashion_go.home.Classifyone_good;
import com.fs.fashion_go.net.ImageCache;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.L;
import com.fs.fashion_go.utils.NetWorkStatic;
import com.fs.fashion_go.utils.SPUtils;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.widget.PullToRefreshView;

public class HomeFragment extends BaseFragment implements OnClickListener,
		NetAccessListener {
	private MQuery mQuery;
	private List<View> views;
	private List<Banner> datas;
	private List<ThreeGoodsInfo> threegoods;
	private List<GetRecommendation> list;
	private ViewPager viewPager;
	private BannerPagerAdapter adapter;
	private RecommendationAdapter recommendationAdapter;
	private View view;
	private ImageView[] tip;// 指示点
	private ViewGroup group;
	private ImageView titiImageView;
	private File cache;
	private PullToRefreshView pullToRefreshView;
	private MyThread thread;

	@Override
	public View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.homefragment, container, false);
		// 禁止键盘自动弹出
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		group = (ViewGroup) view.findViewById(R.id.viewGroup);
		// //上拉下拉刷新
		// new PullToRefreshView(getContext()).onHeaderRefreshComplete();
		// new PullToRefreshView(getContext()).onFooterRefreshComplete();
		// pullToRefreshView = ()

		// 设置viewPager的touch事件，防止子viewPager滑动到最后一张继续滑动导致父viewPager滑动
		viewPager = (ViewPager) view.findViewById(R.id.vp);
		viewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					// 通知其父控件，现在是本控件的操作，不允许拦截，即子控件把滑动事件销毁掉
					view.getParent().requestDisallowInterceptTouchEvent(true);
				}
				return false;
			}
		});
		thread = new MyThread();
		new Thread(thread).start();
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(view);

	}

	@Override
	public void initView() {
		// pullToRefreshView =
		// (PullToRefreshView)view.findViewById(R.id.pulltorefreshview);
		// pullToRefreshView.onHeaderRefreshComplete();
		// pullToRefreshView.onFooterRefreshComplete();
		mQuery.id(R.id.layout_titlemenu).clicked(this);
		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if (!cache.exists()) {
			cache.mkdirs();// 建立一个新的目录
		}
		if (!NetWorkStatic.isNetworkConnected(getActivity())) {
			views = new ArrayList<View>();
			for (int i = 0; i < 4; i++) {
				ImageView iv = new ImageView(getActivity());
				iv.setScaleType(ScaleType.FIT_XY);
				ImageCache.asyncloadImage(iv,
						SPUtils.getPrefString(getActivity(), "image" + i, ""),
						cache);
				views.add(iv);
				adapter = new BannerPagerAdapter(views);
				mQuery.id(R.id.vp).adapter(adapter);
				return;
			}
		}
		getbanner();
	}

	public void getbanner() {
		mQuery.request().setFlag("banner")
				.byGet(Interface.GET_BANNER_LIST, this);
		mQuery.request().setFlag("threegoods")
				.byGet(Interface.GET_THREE_GOODS_AD_INFO, this);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("page", "1");
		mQuery.request().setFlag("recommendation").setParams(map)
				.byPost(Interface.GET_RECOMMENDATION, this);
	}

	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("banner")) {
			if (NetResult.isSuccess(getActivity(), success, object, error)) {
				L.e("banner" + object);
				JSONArray jsonArray = JSONObject.parseObject(object)
						.getJSONArray("data");
				datas = JSON.parseArray(jsonArray.toJSONString(), Banner.class);
				views = new ArrayList<View>();
				for (int i = 0; i < datas.size(); i++) {
					ImageView iv = new ImageView(getActivity());
					iv.setScaleType(ScaleType.FIT_XY);
					mQuery.id(iv).image(datas.get(i).bannerImage);
					// 缓存
					ImageCache.asyncloadImage(iv, datas.get(i).bannerImage,
							cache);
					SPUtils.setPrefString(getActivity(), "image" + i,
							datas.get(i).bannerImage);
					views.add(iv);
				}
				adapter = new BannerPagerAdapter(views);
				mQuery.id(R.id.vp).adapter(adapter);
				// 初始化指示点
				tip = new ImageView[views.size()];
				for (int i = 0; i < tip.length; i++) {
					ImageView imageView = new ImageView(getActivity());
					imageView.setLayoutParams(new LayoutParams(15, 15));
					tip[i] = imageView;
					if (i == 0) {
						tip[i].setBackgroundResource(R.drawable.mall_list_detail_circle_down);
					} else {
						tip[i].setBackgroundResource(R.drawable.mall_list_detail_circle_nor);
					}
					LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
							new ViewGroup.LayoutParams(15, 15));
					layoutParams.leftMargin = 5;
					layoutParams.rightMargin = 5;
					group.addView(imageView, layoutParams);
				}
				viewPager.addOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						setImageBackground(arg0 % views.size());

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

			} else {
				T.showMessage(getActivity(), NetResult.getMsg(object));
			}

		} else if (flag.equals("threegoods")) {
			if (NetResult.isSuccess(getActivity(), success, object, error)) {
				L.e("threegoods" + object);
				JSONArray jsonArray = JSONObject.parseObject(object)
						.getJSONArray("data");
				threegoods = JSON.parseArray(jsonArray.toJSONString(),
						ThreeGoodsInfo.class);
				mQuery.id(R.id.home_good1).image(threegoods.get(0).ADImage);
				mQuery.id(R.id.home_good2).image(threegoods.get(1).ADImage);
				mQuery.id(R.id.home_good3).image(threegoods.get(2).ADImage);
			} else {
				T.showMessage(getActivity(), NetResult.getMsg(object));
			}
		} else if (flag.equals("recommendation")) {
			if (NetResult.isSuccess(getActivity(), success, object, error)) {
				L.e("recommendation" + object);
				JSONArray jsonArray = JSONObject.parseObject(object)
						.getJSONArray("data");
				list = JSON.parseArray(jsonArray.toJSONString(),
						GetRecommendation.class);
				recommendationAdapter = new RecommendationAdapter(getActivity());
				recommendationAdapter.setData(list);
				mQuery.id(R.id.list_home_goods).adapter(recommendationAdapter);
			} else {
				T.showMessage(getActivity(), NetResult.getMsg(object));
			}
		}

	}

	/**
	 * 设置选中的tip的背景
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tip.length; i++) {
			if (i == selectItems) {
				tip[i].setBackgroundResource(R.drawable.mall_list_detail_circle_down);
			} else {
				tip[i].setBackgroundResource(R.drawable.mall_list_detail_circle_nor);
			}
		}
	}

	private int index;

	public class MyThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(3000);
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				int index = viewPager.getCurrentItem();
				index++;
				if (index>=adapter.getCount()) {
					index=0;
				}
				viewPager.setCurrentItem(index);
				break;
			}
			super.handleMessage(msg);
		};
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_titlemenu:
			Intent good_intent = new Intent(getActivity(),
					Classifyone_good.class);
			startActivity(good_intent);
			break;
		// case R.id.title_menuimge:
		// T.showMessage(getActivity(),"你不点击我了哦,嘿嘿");
		// break;
		default:
			break;
		}

	}

}
