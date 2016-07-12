package com.fs.fashion_go;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fs.fashion_go.utils.TextUtil;

/** 
 * app数据,SharedPreferences
 * @author LinPH
 */
public class AppData implements Serializable {
	private Context mcontext;
	private SharedPreferences settings;

	public AppData(Context context) {
		mcontext = context;
		settings = PreferenceManager.getDefaultSharedPreferences(mcontext);
	}

	/**
	 * 获取纬度
	 * @return
	 */
	public double getLatitude() {

		return Double.valueOf(settings.getString("latitude", "0.0"));
	}

	/**
	 * 设置纬度
	 * @param uid
	 */
	public void setLatitude(double latitude) {
		settings.edit().putString("latitude", latitude + "").commit();
	}

	/**
	 * 获取经度
	 * @return
	 */
	public double getLongitude() {

		return Double.valueOf(settings.getString("longitude", "0.0"));
	}

	/**
	 * 设置经度
	 * @param uid
	 */
	public void setLongitude(double longitude) {
		settings.edit().putString("longitude", longitude + "").commit();
	}

	/**
	 * 获取地址
	 * @return
	 */
	public String getAddress() {

		return settings.getString("address", "未知位置");
	}

	/**
	 * 设置地址
	 * @param uid
	 */
	public void setAddress(String address) {
		settings.edit().putString("address", address).commit();
	}

	/**
	 * 获取街道
	 * @return
	 */
	public String getStreet() {

		return settings.getString("street", "这里");
	}

	/**
	 * 设置街道
	 * @param uid
	 */
	public void setStreet(String street) {
		settings.edit().putString("street", street).commit();
	}

	/**
	 * 获取城市
	 * @return
	 */
	public String getCity() {

		return settings.getString("city", "未定位");
	}

	/**
	 * 设置城市
	 * @param uid
	 */
	public void setCity(String city) {
		settings.edit().putString("city", city).commit();
	}

	/**
	 * 获取用户id
	 * @return
	 */
	public String getUserId() {
		return settings.getString("userid", "");
	}

	/**
	 * 设置用户id
	 * @param uid
	 */
	public void setUserId(String uid) {
		settings.edit().putString("userid", uid).commit();
	}

	/**
	 * 获取accessToken
	 * @return
	 */
	public String getAccesstoken() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getString(key, "");
	}

	/**
	 * 设置accessToken
	 * @param accesstoken
	 */
	public void setAccesstoken(String accesstoken) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putString(key, accesstoken).commit();
	}

	/**
	 * 是否登录了
	 * @return
	 */
	public boolean isLogin() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getBoolean(key, false);
	}

	/**
	 * 设置是否登录
	 * @param isLogin
	 */
	public void setLogin(boolean isLogin) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putBoolean(key, isLogin).commit();
	}

	/**
	 * 获取登录名
	 * @return
	 */
	public String getLogin_name() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getString(key, "");
	}

	/**
	 * 设置登录名
	 * @param login_name
	 */
	public void setLogin_name(String login_name) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putString(key, login_name).commit();
	}

	/**
	 * 获取登录密码
	 * @return
	 */
	public String getLogin_pwd() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getString(key, "");
	}

	/**
	 * 设置登录密码
	 * @param login_pwd
	 */
	public void setLogin_pwd(String login_pwd) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putString(key, login_pwd).commit();
	}

	/**
	 * 是否记住密码
	 * @return
	 */
	public boolean isRemeberPwd() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getBoolean(key, false);
	}

	/**
	 * 设置是否记住密码
	 * @param isRemeberPwd
	 */
	public void setRemeberPwd(boolean isRemeberPwd) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putBoolean(key, isRemeberPwd).commit();
	}

	/**
	 * 是否第一次运行程序
	 * @return
	 */
	public boolean isIsfirstrun() {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		return settings.getBoolean(key, true);
	}

	/**
	 * 设置是否第一次运行程序
	 * @param isfirstrun
	 */
	public void setIsfirstrun(boolean isfirstrun) {
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		String key = TextUtil.isEmpty(method) ? "null" : method.toLowerCase().replace("get", "").replace("set", "").replace("is", "");
		settings.edit().putBoolean(key, isfirstrun).commit();
	}

}
