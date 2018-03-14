package com.project.shop.model;

import java.util.Date;

import com.project.shop.common.PageVO;

/**
 *  상품 할인
 *
 */
public class Discount extends PageVO{
	
	/*
	 * 상품 할인 ID
	 */
	private String dcntId;
	/*
	 * 상품 할인 가격
	 */
	private int dcntPrice;
	/*
	 * 상품 할인 시작일
	 */
	private Date dcntStartDate;
	/*
	 * 상품 할인 종료일
	 */
	private Date dcntEndDate;
	/*
	 * 상품 할인 등록자
	 */
	private String dcntRegid;
	/*
	 * 상품 할인 등록일
	 */
	private Date dcntRegdate;
	/*
	 * 상품 할인 수정자
	 */
	private String dcntModid;
	/*
	 * 상품 할인 수정일
	 */
	private Date dcntModdate;
	/*
	 * 상품 할인명
	 */
	private String dcntName;
	/*
	 * 상품 할인 타입
	 */
	private String dcntType;
	
	public String getDcntId() {
		return dcntId;
	}
	public void setDcntId(String dcntId) {
		this.dcntId = dcntId;
	}
	public int getDcntPrice() {
		return dcntPrice;
	}
	public void setDcntPrice(int dcntPrice) {
		this.dcntPrice = dcntPrice;
	}
	public Date getDcntStartDate() {
		return dcntStartDate;
	}
	public void setDcntStartDate(Date dcntStartDate) {
		this.dcntStartDate = dcntStartDate;
	}
	public Date getDcntEndDate() {
		return dcntEndDate;
	}
	public void setDcntEndDate(Date dcntEndDate) {
		this.dcntEndDate = dcntEndDate;
	}
	public String getDcntRegid() {
		return dcntRegid;
	}
	public void setDcntRegid(String dcntRegid) {
		this.dcntRegid = dcntRegid;
	}
	public Date getDcntRegdate() {
		return dcntRegdate;
	}
	public void setDcntRegdate(Date dcntRegdate) {
		this.dcntRegdate = dcntRegdate;
	}
	public String getDcntModid() {
		return dcntModid;
	}
	public void setDcntModid(String dcntModid) {
		this.dcntModid = dcntModid;
	}
	public Date getDcntModdate() {
		return dcntModdate;
	}
	public void setDcntModdate(Date dcntModdate) {
		this.dcntModdate = dcntModdate;
	}
	public String getDcntName() {
		return dcntName;
	}
	public void setDcntName(String dcntName) {
		this.dcntName = dcntName;
	}
	public String getDcntType() {
		return dcntType;
	}
	public void setDcntType(String dcntType) {
		this.dcntType = dcntType;
	}
}
