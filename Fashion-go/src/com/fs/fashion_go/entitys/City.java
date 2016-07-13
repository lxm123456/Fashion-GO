package com.fs.fashion_go.entitys;

import java.io.Serializable;

/**
 * @data 城市实体类
 * @author whz
 * @time 2015年3月6日
 */
public class City implements Serializable {
	private String cName;
	private String cCode;
	private Province province;
	
	public String getCName() {
		return cName;
	}
	public void setCName(String cName) {
		this.cName = cName;
	}
	public String getCCode() {
		return cCode;
	}
	public void setCCode(String cCode) {
		this.cCode = cCode;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}

	
	
	
	
	
}



