package com.fs.fashion_go.login;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TextUtil;
import com.fs.fashion_go.utils.TitleController;

public class FindPassword extends BaseActivity implements OnClickListener,NetAccessListener{
	private MQuery mQuery;
	private String find_phone;
	private String find_code;
	private String find_ed_password;
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.findpassword);
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(this);
	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("找回密码").hideRText();
		mQuery.id(R.id.layout_left).clicked(this);
		mQuery.id(R.id.findpws_btn_getcode).clicked(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.layout_left:
			Intent intent = new Intent(FindPassword.this,Login.class);
			startActivity(intent);
			break;
		case R.id.findpws_btn_getcode:
			T.showMessage(this,"没有接口连接");
			break;
		default:
			break;
		}
	}
	//找回密码
	public void findPassword(){
		find_phone = mQuery.id(R.id.findpws_edphones).getText();
		find_code = mQuery.id(R.id.findpws_btn_getcode).getText();
		find_ed_password = mQuery.id(R.id.findpws_edpassword).getText();
		if (TextUtil.isEmpty(find_phone)) {
			T.showMessage(this,"请输入手机号");
			return;
		}
		if (TextUtil.isEmpty(find_code)) {
			T.showMessage(this,"请输入验证码");
			return;
		}
		if (TextUtil.isEmpty(find_ed_password)) {
			T.showMessage(this,"请输入密码");
			return;
		}
		HashMap<String,String> map =new  HashMap<String, String>();
//		map.put("userPhone",zhuce_phone);
//		map.put("type","");
		mQuery.request().setFlag("findpassword").setParams(map).byPost("",this);
	}
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("findpassword")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				T.showMessage(this,object);
			}
		}
		
	}

}
