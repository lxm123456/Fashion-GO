package com.fs.fashion_go;

import java.io.File;

import android.os.Environment;

import com.fs.fashion_go.utils.FileUtil;
import com.fs.fashion_go.utils.L;

/**
 * 程序常量
 * 
 * @author LinPH 2015-4-8 下午3:02:18
 */
public class Constant {

	public static final boolean ISDEBUG = true;// 是否是测试阶�?

	public static final String WEBROOT = "";//官网
	public static final String WEBImageROOT = "";


	public static String GetWebRoot() {
		String root_temp = WEBROOT;
		File file = new File(Constant.APPPATH + "/root.cfg");
		if (file.exists()) {
			String str = FileUtil.file2str(file);
			str = str.replace("\n", "");
			root_temp = str;
		}
		L.showlog("webroot=" + root_temp);
		return root_temp;
	}

	public static String GetWebImageRoot() {
		String root_temp = WEBImageROOT;
		File file = new File(Constant.APPPATH + "/imageroot.cfg");
		if (file.exists()) {
			String str = FileUtil.file2str(file);
			str = str.replace("\n", "");
			root_temp = str;
		}
		L.showlog("imageroot=" + root_temp);
		return root_temp;
	}

	public final static boolean HASSDCARD = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	public final static String SDCARDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	public final static String APPPATH = HASSDCARD ? SDCARDPATH + "/mf/fs" : "/data/data/com.fs.fashionshop/files";
	public final static String DOWNLOADPATH = APPPATH + "/downLoad";
	public final static String CRASHPATH = APPPATH + "/crash";
	public final static String CACHEPATH = APPPATH + "/cache";

}
