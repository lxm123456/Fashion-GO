package com.fs.fashion_go.home;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.adapter.ClassifyThreeAdapter;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.ClassifyThreeData;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.L;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TitleController;

public class ClassifyThree_goods extends BaseActivity implements OnClickListener, NetAccessListener{
	private MQuery mQuery;
	private Intent intent;
	private List<ClassifyThreeData> classifyThreeDatas;
	private ClassifyThreeAdapter adapter = new ClassifyThreeAdapter(this);
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_classify_three_goods);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(this);
	}

	@Override
	public void initView() {
		new TitleController(this).setLClick(this).setTitle("分类").hideRText();
		mQuery.id(R.id.layout_left).clicked(this);
		intent = this.getIntent();
		postThreeData();
	}
	public void postThreeData(){
		HashMap< String, String> map = new HashMap<String, String>();
		map.put("ClassifyTwoId", intent.getStringExtra("ClassifyTwoId"));
		mQuery.request().setFlag("ClassifyThree").setParams(map).byPost(Interface.GET_CLASSIFY_THREE, this);
	}
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("ClassifyThree")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				L.e("ClassifyThree"+object);
				JSONArray jsonArray = JSONObject.parseObject(object).getJSONArray("data");
				classifyThreeDatas = JSON.parseArray(jsonArray.toJSONString(),ClassifyThreeData.class);
				adapter.setData(classifyThreeDatas);
				mQuery.id(R.id.list_three_goods).adapter(adapter);
				mQuery.id(R.id.list_three_goods).itemClicked(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent threeIntent = new Intent(ClassifyThree_goods.this,Classify_four_ProductList.class);
						threeIntent.putExtra("ClassifyThreeId", classifyThreeDatas.get(position).getClassifyThreeId());
						startActivity(threeIntent);
					}
				});
			}
		}else {
			T.showMessage(this, NetResult.getMsg(object));
		}
		
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

}
