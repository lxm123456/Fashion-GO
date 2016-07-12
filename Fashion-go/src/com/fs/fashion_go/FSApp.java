package com.fs.fashion_go;

import java.util.LinkedList;
import java.util.List;

import com.android.volley.VolleyLog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


public class FSApp extends Application{

	/**
	 * 单例实例�? 方便其他地方访问 A singleton instance of the application class for easy
	 * access in other places
	 */
	public static FSApp mApp;
	public static int SmsSendWaitTime;//短信发�?�剩余时�?

	private  List<Activity> activityList = new LinkedList<Activity>();// 储存打开过的activity

	@Override
	public void onCreate() {
		// initialize the singleton
		super.onCreate();
		VolleyLog.DEBUG = Constant.ISDEBUG;
		mApp = this;
	}
	
	

	// synchronized 用于线程安全，防止多线程同时创建实例
	public static synchronized FSApp getInstance() {
		return mApp;
	}

	// =======================app数据部分
	private static AppData appdata;

	public static AppData getAppdata(Context content) {
		if (appdata == null) {
			appdata = new AppData(content);
		}
		return appdata;
	}

	/* Activity部分 */
	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * �?出所有Activity//�?出程�?
	 */
	public  void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		// System.exit(0);
	}
   
	/**
	 * 返回首页//关闭除了MainActivity以外的Activity
	 */
	public void returnMain() {
		for (Activity activity : activityList) {
			if (activity.getClass().equals(MainActivity.class)) {

			} else {
				activity.finish();
			}
		}
		// System.exit(0);
	}

	// /**
	// * 返回登录�?//关闭除了LoginActivity以外的Activity
	// */
	// public void returnLogin() {
	// for (Activity activity : activityList) {
	// if (activity.getClass().equals(LoginActivity.class)) {
	//
	// } else {
	// activity.finish();
	// }
	// }
	// // System.exit(0);
	// }
}
