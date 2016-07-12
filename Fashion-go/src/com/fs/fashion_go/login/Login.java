package com.fs.fashion_go.login;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.MainActivity;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.entitys.LogoData;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.SPUtils;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TitleController;

public class Login extends BaseActivity implements OnClickListener,
		NetAccessListener {
	private MQuery mq;
	private String logo_phone;
	private String logo_password;

	@Override
	public void createActivity(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.login);
	}

	@Override
	public void initData() {
		mq = new MQuery(this);
	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("登录").hideRText();
		mq.id(R.id.logo_zhuce).clicked(this);
		mq.id(R.id.btn_logo).clicked(this);
		mq.id(R.id.logo_findpws).clicked(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logo_zhuce:
			Intent intent_zhuce = new Intent(Login.this, Register.class);
			startActivity(intent_zhuce);
			break;
		case R.id.btn_logo:
			getLogo();
			break;
		case R.id.logo_findpws:
			Intent findpws_intent = new Intent(Login.this, FindPassword.class);
			startActivity(findpws_intent);
			break;
		default:
			break;
		}
	}

	/**
	 * 发起网络请求
	 */
	public void getLogo() {
		logo_phone = mq.id(R.id.logo_edphone).getText();
		logo_password = mq.id(R.id.logo_edpasswrod).getText();
		if (TextUtils.isEmpty(logo_phone)) {
			T.showMessage(this, "账号不能为空");
			return;
		} 
		if (TextUtils.isEmpty(logo_password)) {
			T.showMessage(this, "密码不能为空");
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", logo_phone);
		map.put("password", logo_password);
//		map.put("userName", "12126214191");
//		map.put("password", "123");
		map.put("system", "android");
		mq.request().setFlag("logo").setParams(map).byPost(Interface.LOGIN, this);
	}

	/**
	 * 请求回调
	 */
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("logo")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				JSONObject jsonObject = JSONObject.parseObject(object)
						.getJSONObject("data");
				LogoData tokenString = JSON.parseObject(
						jsonObject.toJSONString(), LogoData.class);
				SPUtils.setPrefString(this, "accessToken",
						tokenString.getAccessToken());
				String tString = JSONObject.parseObject(object).getString(
						"message");
				T.showMessage(this, tString);
				Intent intent_home = new Intent(Login.this,
						MainActivity.class);
				startActivity(intent_home);
			}
		}
	}

}
