package com.project.shop.model;

import java.util.List;

/**
 * 상품 옵션 VO  
 * @author SieunLim 
 * 
 */

public class ProductOption {

	/** 상품 코드 */
	private String prdtCode;
	/** 상품 컬러 */
	private String prdtColorCode;
	/** 컬러 명*/
	private String colorName;
	/** 상품 사이즈 */
	private String prdtSize;
	/** 상품 재고 */
	private int prdtLaveCount;
	/** 생성자 아이디 */
	private String rgsId;
	/** 생성일 get만*/
	private String regtDtm;
	/** 수정일 get만*/
	private String modDtm;
	/** 수정자 아이디*/
	private String modId;
	
	private String mdprdtCode;
	
	private String mdprdtColorCode;
	
	private String mdprdtSize;
	
	public String getPrdtCode() {
		return prdtCode;
	}
	public void setPrdtCode(String prdtCode) {
		this.prdtCode = prdtCode;
	}

	public String getPrdtColorCode() {
		return prdtColorCode;
	}
	public void setPrdtColorCode(String prdtColorCode) {
		this.prdtColorCode = prdtColorCode;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getPrdtSize() {
		return prdtSize;
	}
	public void setPrdtSize(String prdtSize) {
		this.prdtSize = prdtSize;
	}
	public int getPrdtLaveCount() {
		return prdtLaveCount;
	}
	public void setPrdtLaveCount(int prdtLaveCount) {
		this.prdtLaveCount = prdtLaveCount;
	}
	public String getRgsId() {
		return rgsId;
	}
	public void setRgsId(String rgsId) {
		this.rgsId = rgsId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getRegtDtm() {
		return regtDtm;
	}
	public String getModDtm() {
		return modDtm;
	}
	public String getMdprdtCode() {
		return mdprdtCode;
	}
	public void setMdprdtCode(String mdprdtCode) {
		this.mdprdtCode = mdprdtCode;
	}
	public String getMdprdtColorCode() {
		return mdprdtColorCode;
	}
	public void setMdprdtColorCode(String mdprdtColorCode) {
		this.mdprdtColorCode = mdprdtColorCode;
	}
	public String getMdprdtSize() {
		return mdprdtSize;
	}
	public void setMdprdtSize(String mdprdtSize) {
		this.mdprdtSize = mdprdtSize;
	}

	
}
