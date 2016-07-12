package com.fs.fashion_go.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 判断网络是否可用
 * @author lenovo
 *
 */
public class NetWorkStatic {

	/**
	 * true 可用
	 * @param act
	 * @return
	 */
	    public static boolean isNetworkConnected(Activity act) {  
	        
	       ConnectivityManager manager = (ConnectivityManager) act  
	              .getApplicationContext().getSystemService(  
	                     Context.CONNECTIVITY_SERVICE);  
	        
	       if (manager == null) {  
	           return false;  
	       }  
	        
	       NetworkInfo networkinfo = manager.getActiveNetworkInfo();  
	        
	       if (networkinfo == null || !networkinfo.isAvailable()) {  
	           return false;  
	       }  
	   
	       return true;  
	    }  
	
}
