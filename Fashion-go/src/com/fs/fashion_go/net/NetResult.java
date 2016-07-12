package com.fs.fashion_go.net;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.fs.fashion_go.utils.T;


/**
 * 网络响应
 *
 * @author ping 2014-4-16 上午10:36:09
 */
public class NetResult {

	/**
	 * 有提示，网络连接且请求没有错误?
	 *
	 * @param activity
	 * @param success
	 * @param object
	 * @param error
	 * @return
	 * @author ping
	 * @create 2014-4-19 下午7:05:24
	 */
	public static boolean isSuccess(Activity activity, boolean success, String object, VolleyError error) {
		if (success && object != null) {
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(object);
			} catch (Exception e) {
				
			}
			if (json == null) {
				T.showMessage(activity, "网络请求失败");
			} else if (json.containsKey("errorcode")) {
				if (json.getInteger("errorcode") == 0) {
					return true;
				} else if (json.getInteger("errorcode") == 101) {
					// T.showMessage(activity, json.getString("message"));
					// Intent login = new Intent(activity, LoginActivity.class);
					// activity.startActivity(login);
					// NMApp.getInstance().returnLogin();
				} else if (json.containsKey("message")) {
					T.showMessage(activity, json.getString("message"));
				} else {
					T.showMessage(activity, "网络请求失败");
				}
			} else {
				if (json.containsKey("message")) {
					T.showMessage(activity, json.getString("message"));
				} else {
					T.showMessage(activity, "网络请求失败");
				}
			}

			return false;
		} else {
			// T.showMessage(activity, "网络访问失败");
			VolleyErrorMsg.printError(true, activity, error);
		}
		return false;
	}

	/**
	 * 无提示，网络连接且请求没有错误?
	 *
	 * @param activity
	 * @param success
	 * @param object
	 * @param error
	 * @return
	 * @author ping
	 * @create 2014-4-19 下午7:05:24
	 */
	public static boolean isSuccess2(Activity activity, boolean success, String object, VolleyError error) {
		if (success && object != null) {
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(object);
			} catch (Exception e) {
			}

			if (json == null) {
				T.showMessage(activity, "网络请求失败");
			} else if (json.containsKey("errorcode")) {
				if (json.getInteger("errorcode") == 0) {
					return true;
				}
			}
		} else {
			VolleyErrorMsg.printError(false, activity, error);
		}
		return false;
	}

	public static String getMsg(String object) {
		String result = null;
		if (object == null) {
			result = "网络请求失败";
		} else {
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(object);
			} catch (Exception e) {
			}
			if (json == null) {
				// 网络错误，取到非json数据
			} else if (json.containsKey("message")) {
				try {
					result = json.getString("message");
				} catch (Exception e) {
				}
			}
		}
		return result == null ? "unknow message" : result;
	}
}
