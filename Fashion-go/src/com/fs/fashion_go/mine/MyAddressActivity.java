package com.fs.fashion_go.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.utils.TitleController;

/**
 * 收获地址界面
 * @author Administrator
 *
 */
public class MyAddressActivity extends BaseActivity implements OnClickListener,NetAccessListener{

	private MQuery mq; 
	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_mine_address);
	}

	@Override
	public void initData() {
		mq = new MQuery(this);
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		 new TitleController(this).setTitle("收获地址").setLClick(this).showRText().setRText("新增").setRClick(this);;

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;
		case R.id.layout_right:
			intent = new Intent(this, AddAddressActivity.class);
			startActivity(intent);
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
