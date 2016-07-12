package com.fs.fashion_go.utils;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion_go.R;




/**
 * 吐司信息打印类
 * 
 * @author ping
 * @create 2014-5-23 上午9:01:16
 */
public class T {
	private static Toast toast;

	/** Hide the toast, if any. */
	public static void hideToast() {
		if (null != toast) {
			toast.cancel();
		}
	}

	/** 显示自定义Toast提示(来自res) **/
	public static void showMessage(Context context, int resId) {
		showMessage(context, context.getString(resId));
	}

	/** 显示自定义Toast提示(来自String) **/
	public static void showMessage(final Context context, final String text) {
		if (TextUtil.isEmpty(text)) return;
		if (context instanceof Activity) {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (toast == null) {
						toast = new Toast(context);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.setDuration(Toast.LENGTH_SHORT);
					}
					View toastRoot = LayoutInflater.from(context).inflate(
							R.layout.common_toast, null);
					((TextView) toastRoot.findViewById(R.id.toast_text))
							.setText(text);
					toast.setView(toastRoot);
					toast.show();
				}
			});
		} else {
			if (toast == null) {
				toast = new Toast(context);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
			}
			View toastRoot = LayoutInflater.from(context).inflate(
					R.layout.common_toast, null);
			((TextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
			toast.setView(toastRoot);
			toast.show();
		}
	}
}
