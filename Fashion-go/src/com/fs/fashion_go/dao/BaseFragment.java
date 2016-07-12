package com.fs.fashion_go.dao;

import com.fs.fashion_go.utils.L;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 基本的Fragment
 */
public abstract class BaseFragment extends Fragment implements GUIObserver {
	private static final String TAG = "BaseFragment";

	public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	public abstract void initData();

	public abstract void initView();

	/**
	 * Fragment创建时调用的获取视图方法
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = createView(inflater, container, savedInstanceState);

		String key = BaseFragment.this.getClass().getName();
		L.i(TAG, "new:" + key);
		GUIConcrete.addObserver(BaseFragment.this);

		initData();
		initView();

		return view;
	}

	/**
	 * Activity�?出时，从Activity列表移除
	 * @return
	 */
	@Override
	public void onDestroy() {
		L.i(TAG, "onDestroy:" + getClass().getName());
		GUIConcrete.removeObserver(getClass());
		super.onDestroy();
	}

	/**
	 * 通知�?有已存在的activity更新数据
	 */
	@Override
	public void notifyData(Object data) {
		GUIConcrete.notifyData(data);
	}

	/**
	 * 不做任何事，只是用于避免子类都强制实现改方式
	 */
	@Override
	public void OnDataUpdate(Object data) {
	}

}
