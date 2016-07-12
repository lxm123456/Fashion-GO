package com.fs.fashion_go.home;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.L;
import com.fs.fashion_go.utils.T;

public class Classify_four_ProductList extends BaseActivity implements OnClickListener,NetAccessListener {
	private MQuery mQuery;
	private RadioButton radiobutton_related,radiobutton_sales,radiobutton_price,radiobutton_newproduct;
	private String type="0";
	private Intent intent;
	
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.classify_four_product_list);
		//设置不让搜索键盘自动弹出
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		intent=this.getIntent();
		classify_fourPost(type);
		
	}

	@Override
	public void initData() {
		mQuery = new MQuery(this);
	}

	@Override
	public void initView() {
		mQuery.id(R.id.layout_left).clicked(this);
		
		radiobutton_related=(RadioButton)findViewById(R.id.radiobutton_related);
		radiobutton_sales=(RadioButton)findViewById(R.id.radiobutton_sales);
		radiobutton_price=(RadioButton)findViewById(R.id.radiobutton_price);
		radiobutton_newproduct=(RadioButton)findViewById(R.id.radiobutton_newproduct);
		
		radiobutton_related.setOnClickListener(this);
		radiobutton_sales.setOnClickListener(this);
		radiobutton_price.setOnClickListener(this);
		radiobutton_newproduct.setOnClickListener(this);
		
	}
	public void classify_fourPost(String type){
		L.e("classify_fourPost"+intent.getStringExtra("ClassifyThreeId"));
		HashMap<String, String> map =  new HashMap<String, String>();
		map.put("keyword", "");//搜索
		map.put("ClassifyThreeId",intent.getStringExtra("ClassifyThreeId"));
		map.put("type",type);
		map.put("page", "1");
		mQuery.request().setFlag("GET_GOODS_LIST").setParams(map).byPost(Interface.GET_GOODS_LIST, this);
	}
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("GET_GOODS_LIST")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				L.e("GET_GOODS_LIST"+object);
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
		case R.id.radiobutton_related:
			T.showMessage(this, "我来美女！嘿嘿");
			lighton(0);
			break;
		case R.id.radiobutton_sales:
			lighton(1);
			break;
		case R.id.radiobutton_price:
			lighton(2);
			break;
		case R.id.radiobutton_newproduct:
			lighton(3);
			break;
		default:
			break;
		}
	}
	//设置RadioButton按钮未点击时的状态
	private void lightoff(){
		radiobutton_related.setChecked(false);
		radiobutton_price.setChecked(false);
		radiobutton_sales.setChecked(false);
		radiobutton_newproduct.setChecked(false);
	}
	private void lighton(int i){
		lightoff();
		switch (i) {
		case 0:
			type="0";
			classify_fourPost(type);
			radiobutton_related.setChecked(true);
			break;
		case 1:
			type="1";
			classify_fourPost(type);
			radiobutton_sales.setChecked(true);
			
			break;
		case 2:
			type="2";
			classify_fourPost(type);
			radiobutton_price.setChecked(true);
			
			break;
		case 3:
			type="3";
			classify_fourPost(type);
			radiobutton_newproduct.setChecked(true);
			break;
		default:
			break;
		}
	}
}
