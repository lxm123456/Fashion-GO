package com.fs.fashion_go;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.MyPagerAdapter;
import com.fs.fashion_go.dao.BaseFramActivity;
import com.fs.fashion_go.fragment.HomeFragment;
import com.fs.fashion_go.fragment.MineFragment;
import com.fs.fashion_go.fragment.MoreFragment;
import com.fs.fashion_go.fragment.ShoppCarFragment;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;

public class MainActivity extends BaseFramActivity implements OnClickListener,
		NetAccessListener {
	private MQuery mQuery;
	private ViewPager viewPager;
	private List<Fragment> fragmentList;
	private MyPagerAdapter aPagerAdapter;

	@SuppressWarnings("deprecation")
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		// viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				tabselete(arg0);
				viewPager.setCurrentItem(arg0);

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
	}

	@Override
	public void initData() {
		mQuery = new MQuery(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void initView() {
		mQuery.id(R.id.layout_home).clicked(this);
		mQuery.id(R.id.layout_shoppingcar).clicked(this);
		mQuery.id(R.id.layout_mine).clicked(this);
		mQuery.id(R.id.layout_more).clicked(this);
		tabselete(0);
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new HomeFragment());
		fragmentList.add(new ShoppCarFragment());
		fragmentList.add(new MineFragment());
		fragmentList.add(new MoreFragment());

		aPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), this,
				fragmentList);
		mQuery.id(R.id.viewpager).adapter(aPagerAdapter);
		viewPager.setAdapter(aPagerAdapter);

	}

	// ����ͼ�������
	private void resetImgs() {
		mQuery.id(R.id.iv_home).image(R.drawable.ico_home_unin);
		mQuery.id(R.id.iv_shoppingcar).image(R.drawable.ico_shoppingcar_unin);
		mQuery.id(R.id.iv_mine).image(R.drawable.ico_mine_unin);
		mQuery.id(R.id.iv_more).image(R.drawable.ico_more_unin);
		mQuery.id(R.id.tv_home).textColor(Color.parseColor("#606060"));
		mQuery.id(R.id.tv_shoppingcar).textColor(Color.parseColor("#606060"));
		mQuery.id(R.id.tv_mine).textColor(Color.parseColor("#606060"));
		mQuery.id(R.id.tv_more).textColor(Color.parseColor("#606060"));

	}

	private void selete(int i) {
		resetImgs();
		switch (i) {
		case 0:
			mQuery.id(R.id.iv_home).image(R.drawable.ico_home_in);
			mQuery.id(R.id.tv_home).textColor(Color.parseColor("#fc3c4a"));
			break;
		case 1:
			mQuery.id(R.id.iv_shoppingcar).image(R.drawable.ico_shoppingcar_in);
			mQuery.id(R.id.tv_shoppingcar).textColor(
					Color.parseColor("#fc3c4a"));
			break;
		case 2:
			mQuery.id(R.id.iv_mine).image(R.drawable.ico_mine_in);
			mQuery.id(R.id.tv_mine).textColor(Color.parseColor("#fc3c4a"));
			break;
		case 3:
			mQuery.id(R.id.iv_more).image(R.drawable.ico_more_in);
			mQuery.id(R.id.tv_more).textColor(Color.parseColor("#fc3c4a"));
			break;
		default:
			break;
		}
	}

	private void tabselete(int i) {
		selete(i);
		viewPager.setCurrentItem(i);// ���õ�ǰ��ʾ��ǩҳ
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_home:
			tabselete(0);
			break;
		case R.id.layout_shoppingcar:
			tabselete(1);
			break;
		case R.id.layout_mine:
			tabselete(2);
			break;
		case R.id.layout_more:
			tabselete(3);
			break;

		default:
			break;
		}
	}

	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		// TODO Auto-generated method stub

	}

}
