package com.fs.fashion_go.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 具体主题角色
 */
public class GUIConcrete {
	private static Map<String, GUIObserver> GUIs;

	/**
	 * 增加�?个视图观察�??
	 * @param watcher
	 */
	public static void addObserver(GUIObserver watcher) {
		if (GUIs == null)
			GUIs = new HashMap<String, GUIObserver>();
		GUIs.remove(watcher.getClass().getName());
		GUIs.put(watcher.getClass().getName(), watcher);
	}

	/**
	 * 移除�?个视图观察�??
	 * @param watcher
	 */
	public static void removeObserver(GUIObserver watcher) {
		removeObserver(watcher.getClass());
	}

	/**
	 * 移除�?个视图观察�??
	 * @param clas
	 */
	public static void removeObserver(Class clas) {
		if (GUIs == null)
			GUIs = new HashMap<String, GUIObserver>();
		GUIs.remove(clas.getName());
	}

	/**
	 * 移除�?有视图观察�??
	 */
	public static void removeAll() {
		GUIs = new HashMap<String, GUIObserver>();
	}

	/**
	 * 提醒�?有观察�?�更新数�?
	 * @param data
	 */
	public static void notifyData(Object data) {
		if (GUIs == null)
			GUIs = new HashMap<String, GUIObserver>();
		for (String key : GUIs.keySet()) {
			GUIObserver ob = GUIs.get(key);
			ob.OnDataUpdate(data);
		}
	}

	/**
	 * 取得�?个视图观察�??
	 * @param clas
	 * @return
	 */
	public static GUIObserver getObserver(Class clas) {
		if (GUIs == null)
			GUIs = new HashMap<String, GUIObserver>();
		return GUIs.get(clas.getName());
	}

}
