package com.project.shop.model;

import java.util.Date;

public class ProductDiscount {

	/**
	 * 상품 코드
	 */
	private String prdtCode;
	/*
	 * 상품할인 아이디
	 */
	private int dcntSeq;
	/*
	 * 등록자 ID
	 */
	private String prdtDcntRegid;
	/*
	 * 등록일 
	 */
	private Date prdtDcntRegdate;
	
	public String getPrdtCode() {
		return prdtCode;
	}
	public void setPrdtCode(String prdtCode) {
		this.prdtCode = prdtCode;
	}
	public int getDcntSeq() {
		return dcntSeq;
	}
	public void setDcntSeq(int dcntSeq) {
		this.dcntSeq = dcntSeq;
	}
	public String getPrdtDcntRegid() {
		return prdtDcntRegid;
	}
	public void setPrdtDcntRegid(String prdtDcntRegid) {
		this.prdtDcntRegid = prdtDcntRegid;
	}
	public Date getPrdtDcntRegdate() {
		return prdtDcntRegdate;
	}
	public void setPrdtDcntRegdate(Date prdtDcntRegdate) {
		this.prdtDcntRegdate = prdtDcntRegdate;
	}
	
}
