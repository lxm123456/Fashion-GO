package com.fs.fashion_go.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;

public class MyOrderInfoActivity extends BaseActivity implements NetAccessListener{


	private MQuery mq;
	private Activity mActivity;
	public static void load(Activity activity,String flag){
	Intent intent = new Intent(activity,MyOrderInfoActivity.class);
	
	activity.startActivity(intent);
}
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initData() {
		mActivity = this;
	}

	@Override
	public void initView() {
	}
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		// TODO Auto-generated method stub
		
	}

}
