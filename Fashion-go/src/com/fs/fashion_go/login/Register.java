package com.fs.fashion_go.login;

import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.example.fashion_go.R;
import com.fs.fashion_go.dao.BaseActivity;
import com.fs.fashion_go.net.Interface;
import com.fs.fashion_go.net.MQuery;
import com.fs.fashion_go.net.NetAccess.NetAccessListener;
import com.fs.fashion_go.net.NetResult;
import com.fs.fashion_go.utils.T;
import com.fs.fashion_go.utils.TitleController;

public class Register extends BaseActivity implements OnClickListener,NetAccessListener{
	private String zhuce_phone;//手机号码
	private String zhuce_getcode;//验证码
	private String zhuce_passwrod;//密码
	private String zhuce_invitecode;//邀请码
	private String zhuce_reqpassword;//确认密码
	private MQuery mQuery;
	private boolean check = true;
	@Override
	public void createActivity(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mQuery = new MQuery(this);
	}

	@Override
	public void initView() {
		new TitleController(this).setTitle("注册").hideRText();
		mQuery.id(R.id.layout_left).clicked(this);
		mQuery.id(R.id.zhuce_getcode).clicked(this);
		mQuery.id(R.id.zhuce_btnqueren).clicked(this);
		mQuery.id(R.id.checkbox).clicked(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_left:
			Intent intent = new Intent(Register.this,Login.class);
			startActivity(intent);
			break;
		case R.id.zhuce_getcode:
			getCodeData();
			break;
		case R.id.zhuce_btnqueren:
			registerData();
			break;
		case R.id.checkbox:
//			if (!check) {
//				mQuery.id(R.id.zhuce_btnqueren).background(R.drawable.btn_check_register);
//				check=true;
//			}else {
//				mQuery.id(R.id.zhuce_btnqueren).background(R.drawable.btn_uncheck_register);
//				check=false;
//			}
			break;
		default:
			break;
		}
		
	}
	public void getCodeData(){
		zhuce_phone = mQuery.id(R.id.zhuce_edphones).getText();
		if (TextUtils.isEmpty(zhuce_phone)) {
			T.showMessage(this,"请输入手机号");
			return;
		}
		HashMap<String,String> map =new  HashMap<String, String>();
		map.put("userPhone",zhuce_phone);
		map.put("type","");
		mQuery.request().setFlag("getcode").setParams(map).byPost(Interface.GetVerify,this);

	}
	public void registerData(){
		zhuce_phone = mQuery.id(R.id.zhuce_edphones).getText();
		zhuce_getcode = mQuery.id(R.id.zhuce_edcode).getText();
		zhuce_invitecode=mQuery.id(R.id.zhuce_edinvitecode).getText();
		zhuce_passwrod=mQuery.id(R.id.zhuce_edpws).getText();
		zhuce_reqpassword=mQuery.id(R.id.zhuce_edquerenpws).getText();
		try {
			if (TextUtils.isEmpty(zhuce_phone)) {
				T.showMessage(this,"请输入手机号");
				return;
			}
			if (TextUtils.isEmpty(zhuce_getcode)) {
				T.showMessage(this,"请输入验证码");
				return;
			}
			if (TextUtils.isEmpty(zhuce_invitecode)) {
				T.showMessage(this, "请输入邀请码");
				return;
			}
			if (TextUtils.isEmpty(zhuce_passwrod)) {
				T.showMessage(this, "请输入密码");
				return;
			}
			if (TextUtils.isEmpty(zhuce_reqpassword)) {
				T.showMessage(this, "确认密码");
				return;
			}
			if (!zhuce_passwrod.equals(zhuce_reqpassword)) {
				T.showMessage(this, "输入的密码和确认密码不一致");
				return;
			}
			HashMap<String,String> mapregister =new  HashMap<String, String>();
			mapregister.put("userPhone",zhuce_phone);//电话号码
			mapregister.put("verificationCode",zhuce_getcode);//验证码
			mapregister.put("password",zhuce_passwrod);//密码
			mapregister.put("invitecode","");//邀请码
			mapregister.put("registerSys","android");//注册系统
			mapregister.put("SIM","");//手机运营商
			mapregister.put("IMEI","");//手机串码
			mQuery.request().setFlag("register").setParams(mapregister).byPost(Interface.REGISTER,this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 请求回调
	 */
	@Override
	public void onAccessComplete(boolean success, String object,
			VolleyError error, String flag) {
		if (flag.equals("getcode")) {
			if (NetResult.isSuccess(this, success, object, error)) {
				String tString = JSONObject.parseObject(object).getString("data");
				T.showMessage(this, "验证码是："+tString);
			}
		}else if (flag.equals("register")) {
			if (NetResult.isSuccess(this, success, object, error)) {
//				String tString = JSONObject.parseObject(object).getString("message");
//				T.showMessage(this,NetResult.getMsg(object));
				T.showMessage(this,NetResult.getMsg(object));
			}
		}
		
	}

}
