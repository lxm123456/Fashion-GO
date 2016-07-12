package com.fs.fashion_go.mine;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.Province;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.TitleController;

public class MyInfoActivity extends BaseActivity implements OnClickListener, NetAccessListener {

	private List<Province> list;
	private MQuery mq;
	private Activity mb;
//	public static void load(Activity activity,String flag){
//		Intent intent = new Intent(activity,MyInfoActivity.class);
//		intent.putExtra("flag", flag);
//		activity.startActivity(intent);
//	}
	@Override
	//设置
	public void createActivity(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_info);
	}

	@Override
	public void initData() {
        mq = new MQuery(this);
	}

	@Override
	public void initView() {
        new TitleController(this).setLClick(this).setTitle("基本信息").hideRText();
        mq.id(R.id.button1).clicked(this);
        mq.id(R.id.layout_left).clicked(this);
        mq.id(R.id.tv_title).text("首页");
        getData();
	}

	//发起网络请求
	private void getData(){
		HashMap<String, String> map = new HashMap<String, String>();
		mq.request().setFlag("get").showDialog(false).byPost(Interface.GET_PROVINCE_LIST, this);
	}
	private void getData2(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "");
		mq.request().setFlag("gets").showDialog(false).setParams(map).byPost("url", this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_left:
			
			break;
		case R.id.button1:
			MyOrderInfoActivity.load( mb ,"");
			break;
		default:
			break;
		}
	}

	/**
	 * 请求回调
	 */
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (NetResult.isSuccess(this, success, object, error)) {
			if (flag.equals("get")) {
				System.out.println(">>>>>>>>>>>>>>"+object);
				JSONObject jsonObject = JSONObject.parseObject(object);
				JSONArray jsaArray = jsonObject.getJSONArray("data");
				list = JSON.parseArray(jsaArray.toJSONString(), Province.class);
				System.out.println(">>>>>>>>>>>>>>"+list.get(1).getpCode());
			}else if (flag.equals("gets")) {
				
			}
		}
	}
}
