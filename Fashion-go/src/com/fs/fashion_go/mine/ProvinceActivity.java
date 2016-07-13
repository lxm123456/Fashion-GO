package com.fs.fashion_go.mine;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.ProvinceAdapter;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.Province;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.TitleController;

/**
 * 省份界面
 * @author Administrator
 *
 */
public class ProvinceActivity extends BaseActivity implements OnClickListener,NetAccessListener{

	private MQuery mq;
	private List<Province> list;
	private ProvinceAdapter adapter;
	private int type = 0;//进入类型 0直接选择省份，1.跳到城市列表
	
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.activity_province);
	}

	@SuppressWarnings("null")
	@Override
	public void initData() {
		Intent intent = this.getIntent();
		type = intent.getIntExtra("type", 0);
		mq = new MQuery(this);
		list = new ArrayList<Province>();
		adapter = new ProvinceAdapter(this);
		adapter.setType(type);
		mq.id(R.id.lv_city).adapter(adapter);
		mq.request().byGet(Interface.GET_PROVINCE_LIST, this);
	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("省份").setLClick(this).hideRText();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		// TODO Auto-generated method stub
		if (NetResult.isSuccess(this, success, object, error)) {
			JSONObject jo = JSONObject.parseObject(object);
			JSONArray ja = jo.getJSONArray("data");
			list = ja.parseArray(ja.toJSONString(), Province.class);
			adapter.setData(list);
		}
		
	}

}
