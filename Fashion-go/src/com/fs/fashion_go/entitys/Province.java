package com.fs.fashion_go.entitys;

import java.io.Serializable;

/**
 * @data 省份实体类
 */
public class Province implements Serializable {
	private String pName;
	private String pCode;
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	public String getPCode() {
		return pCode;
	}
	public void setPCode(String pCode) {
		this.pCode = pCode;
	}
	
	
}



