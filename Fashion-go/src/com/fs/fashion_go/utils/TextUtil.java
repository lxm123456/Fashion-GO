package com.fs.fashion_go.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

public class TextUtil {

	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}

//	public static boolean isEmpty(CharSequence chars) {
//		return chars == null || chars.equals("");
//		return chars == null || isEmpty(chars.toString());
//	}
	
	public static String conversion(String tag){
		String result = "未知";
		try {
			double l= Double.parseDouble(tag);
			if(l < 1000 ){
				return tag;
			}
			
			String d = l/1000+"";
			if(d.contains(".")){
				result = d.substring(0, d.indexOf(".")+2);
			}else{
				result = d;
			}
			
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	
	/**
	 * 判断是否全部有字母和数字组成
	 * @param name
	 * @return
	 */
	public static boolean isLimit(String name){
		//^.[a-zA-Z]\w{m,n}$ 由m-n位的字母数字或下划线组成
		Pattern p = Pattern.compile("[a-zA-Z0-9_@]*");
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	/**
	 * 判断是否是电话号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static String getdistance(String dis) {
		int disint = -1;
		try {
			disint = Integer.parseInt(dis);
		} catch (Exception e) {}
		if (disint == -1) {
			try {
				disint = (int)Double.parseDouble(dis);
			} catch (Exception e) {}
		}
		return getdistance(disint);

	}
	
	public static String getdistance(float dis) {
		int disint = -1;
		try {
			disint = (int)dis;
		} catch (Exception e) {}
		return getdistance(disint);
	}
	
	/**
	 * 从米获取距离
	 * @return
	 */
	public static String getdistance(int dis) {
		if (dis<0) return "未知";
		
		String resu;
//		if (dis>=1000000) {
//			double Kdist = dis/1000;
////			resu = (int)Kdist+ "k千米";
//			resu = (int)Kdist + "千米";
//		} else
			if (dis >=1000) {
			double Kdist = dis/1000.0;
			resu = String.format("%.2f", Kdist) + "km";//保留两位小数
		} else {
			resu = dis + "m";//原始距离
		}
		
		return resu;
	}
    
	  /**
     * 判断email格式是否正确
     */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	/**
	 * 检测符合自定义表情字符串格式
	 * @param text
	 * @return
	 */
	public static ArrayList<String> isHaveFace(CharSequence text){
		String str = "\\[.*?\\]";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(text);
		ArrayList<String> strlist = new ArrayList<String>();
		while(m.find()){
//			m.group();
			strlist.add(m.group());
		}
		return strlist;
	}
	/**
	 * String转换到int
	 * @param str
	 * @param defvalue默认文本
	 * @return
	 */
	public static int String2int (String str,int defvalue) {
		int tem = defvalue;
		try {
			tem = Integer.parseInt(str);
		} catch (Exception e) {
		}
		return tem;
	}
	/**
	 *  压缩字符串  
	 */
	// 压缩   
	  public static String compress(String str) throws IOException {   
	    if (str == null || str.length() == 0) {   
	      return str;   
	    }   
	    ByteArrayOutputStream out = new ByteArrayOutputStream();   
	    GZIPOutputStream gzip = new GZIPOutputStream(out);   
	    gzip.write(str.getBytes());   
	    gzip.close();   
	    return out.toString("ISO-8859-1");
	  }
}
