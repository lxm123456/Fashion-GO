package com.fs.fashion_go.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.AreaAdapter;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.Area;
import com.fs.fashion_go.entitys.City;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.TitleController;

/**
 * 区域列表界面
 * 
 * @author Administrator
 * 
 */
public class AreaActivity extends BaseActivity implements OnClickListener,
		NetAccessListener {

	private MQuery mq;
	private List<Area> list;
	private AreaAdapter adapter;
	private City city;
	private int type = 0;

	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_province);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		Intent intent = this.getIntent();
		city = (City) intent.getSerializableExtra("city");
		type = intent.getIntExtra("type", 0);
		mq = new MQuery(this);
		list = new ArrayList<Area>();
		adapter = new AreaAdapter(this);
		adapter.setType(type);
		mq.id(R.id.lv_city).adapter(adapter);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("cCode", city.getCCode());
		mq.request().setParams(params).byPost(Interface.GET_AREA_LIST, this);

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		new TitleController(this).setTitle("区域").setLClick(this).hideRText();

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
		if (NetResult.isSuccess(this, success, object, error)) {
			JSONObject jo = JSONObject.parseObject(object);
			JSONArray ja = jo.getJSONArray("data");
			list = ja.parseArray(ja.toJSONString(), Area.class);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCity(city);
			}
			adapter.setData(list);
		}

	}

}
