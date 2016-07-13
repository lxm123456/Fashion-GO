package com.fs.fashion_go.home;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.ClassifyOneAdapter;
import com.fs.fashion_go.adapter.ClassifytwoAdapter;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.ClassifyoneData;
import com.fs.fashion_go.entitys.ClassifytwoData;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.L;
import com.fs.fashion_go.utils.T;

public class Classifyone_good extends BaseActivity implements OnClickListener,NetAccessListener{
	private MQuery mQuery;
	private List<ClassifyoneData> classifyoneDatas;
	private List<ClassifytwoData> classifytwoDatas;
	private ClassifyOneAdapter oneAdapter = new ClassifyOneAdapter(Classifyone_good.this);
	private ClassifytwoAdapter twoAdapter = new ClassifytwoAdapter(Classifyone_good.this);
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.acitivity_one_classifygoods);
		//禁止键盘自动弹出
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	@Override
	public void initData() {
		mQuery = new MQuery(this);
	}
	@Override
	public void initView() {
		mQuery.id(R.id.layout_left).clicked(this);
		getClassifyone();
		listViewClick();
		gridViewClick();
	}
	//获取一级分类
	public void getClassifyone(){
		mQuery.request().setFlag("classifyone").byGet(Interface.GET_CLASSIFY_ONE,this);
	}
	//获取二级分类
	public void getClassifytwo(String classifyId){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("classifyId", classifyId);
		mQuery.request().setFlag("ClassifyTwoId").setParams(map).byPost(Interface.GET_CLASSIFY_TWO,this);
	}
	//Item条目的列表点击监听事件
	public void listViewClick(){
		mQuery.id(R.id.list_one_name).itemClicked(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
					ClassifyOneAdapter.selected=position;
					oneAdapter.notifyDataSetChanged();
					getClassifytwo(classifyoneDatas.get(position).getClassifyId());
			}
		});
	}
	public void gridViewClick(){
		mQuery.id(R.id.gview_one_goods).itemClicked(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent threeIntent = new Intent(Classifyone_good.this,ClassifyThree_goods.class);
				threeIntent.putExtra("ClassifyTwoId", classifytwoDatas.get(position).getClassifyTwoId());
				startActivity(threeIntent);
			}
		});
	}
	/*
	 * 请求成功的回调
	 * (non-Javadoc)
	 * @see com.fs.net.NetAccess.NetAccessListener#onAccessComplete(boolean, java.lang.String, com.android.volley.VolleyError, java.lang.String)
	 */
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("classifyone")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				L.e("classifyone"+object);
				JSONArray jsonArray = JSONObject.parseObject(object).getJSONArray("data");
				classifyoneDatas = JSON.parseArray(jsonArray.toJSONString(),ClassifyoneData.class);
				oneAdapter.setData(classifyoneDatas);
				mQuery.id(R.id.list_one_name).adapter(oneAdapter);
				getClassifytwo(classifyoneDatas.get(0).getClassifyId());
			}else {
				T.showMessage(this,NetResult.getMsg(object));
			}
		}else if (flag.equals("ClassifyTwoId")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				L.e("ClassifyTwoId"+object);
				JSONArray jsonArray = JSONObject.parseObject(object).getJSONArray("data");
				classifytwoDatas = JSON.parseArray(jsonArray.toJSONString(),ClassifytwoData.class);
				twoAdapter.setData(classifytwoDatas);
				mQuery.id(R.id.gview_one_goods).adapter(twoAdapter);
			}else {
				T.showMessage(this,NetResult.getMsg(object));
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;
		default:
			break;
		}
	}

}
