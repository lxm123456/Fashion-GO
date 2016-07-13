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
import com.fs.fashion_go.adapter.CityAdapter;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.City;
import com.fs.fashion_go.entitys.Province;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.L;
import com.fs.fashion_go.utils.TitleController;

/**
 * 城市列表界面
 * 
 * @author Administrator
 * 
 */
public class CityActivity extends BaseActivity implements OnClickListener,
		NetAccessListener {

	private MQuery mq;
	private List<City> list;
	private CityAdapter adapter;
	private Province province;
	private int type = 0; // 进入类型 ： 0.直接选择城市 1.跳到区域列表

	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_province);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		Intent intent = this.getIntent();
		province = (Province) intent.getSerializableExtra("province");
		type = intent.getIntExtra("type", 0);
		mq = new MQuery(this);
		list = new ArrayList<City>();
		adapter = new CityAdapter(this);
		adapter.setType(type);
		mq.id(R.id.lv_city).adapter(adapter);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("pCode", province.getPCode());
		mq.request().setParams(params).byPost(Interface.GET_CITY_LIST, this);

	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("城市").setLClick(this).hideRText();
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
		L.e("...." + object);
		if (NetResult.isSuccess(this, success, object, error)) {
			JSONObject jo = JSONObject.parseObject(object);
			JSONArray ja = jo.getJSONArray("data");
			list = ja.parseArray(ja.toJSONString(), City.class);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setProvince(province);
			}
			adapter.setData(list);
		}
	}

}
