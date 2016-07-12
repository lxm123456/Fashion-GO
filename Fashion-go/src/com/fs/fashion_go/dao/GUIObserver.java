package com.fs.fashion_go.dao;

/**
 * 抽象观察者接�?
 */
public interface GUIObserver {
	// 通知�?有已打开的Activity更新数据
	public void notifyData(Object data);

	// �?有已打开的Activity更新数据时触发的事件
	public void OnDataUpdate(Object data);
}
