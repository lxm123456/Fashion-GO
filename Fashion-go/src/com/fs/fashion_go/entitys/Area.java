package com.fs.fashion_go.entitys;

import java.io.Serializable;

/**
 * @data 地区实体类
 * @author whz
 * @time 2015年3月6日
 */
public class Area implements Serializable {
	private String area;
	private String aCode;
	private City city;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getACode() {
		return aCode;
	}
	public void setACode(String aCode) {
		this.aCode = aCode;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	
}



