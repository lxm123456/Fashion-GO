package com.fs.fashion_go.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘处理工具
 * 
 * @author ping
 * @create 2014-4-16 下午5:35:20
 */
public class KeyboradUtil {
	/**
	 * 打开软键盘
	 * @param titleInput
	 */
	public static void showkeyboard(final EditText titleInput) {
		titleInput.setFocusable(true);
		titleInput.requestFocus();
		(new Handler()).postDelayed(new Runnable() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) titleInput.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}, 150);
	}

	/**
	 * 关闭软键盘
	 */
	public static void closeKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 得到InputMethodManager的实例
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 设置点击EditText外的控件自动隐藏软件盘
	 * 
	 * @param view
	 * @param activity
	 */
	public static void setupUI(View view, final Activity activity) {

		// Set up touch listener for non-text box views to hide keyboard.

		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// hideSoftKeyboard(IssueArticleWorldActivity.this); //
					// Main.this是我的activity名
					KeyboradUtil.closeKeyboard(activity);
					return false;
				}

			});

		}

		// If a layout container, iterate over children and seed recursion.

		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView, activity);

			}

		}

	}
}
