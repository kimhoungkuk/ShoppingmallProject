package com.project.shop.model;

import java.util.Date;
import java.util.List;

import com.project.shop.common.PageVO;

/**
 *  상품 할인
 *
 */
public class Discount extends PageVO{
	
	/*
	 * 상품 할인 ID
	 */
	private int dcntSeq;
	/*
	 * 상품 할인명
	 */
	private String dcntName;
	/*
	 * 상품 할인 가격
	 */
	private int dcntPrice;
	/*
	 * 상품 할인 타입
	 */
	private String dcntType;
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
	private String dcntRegId;
	/*
	 * 상품 할인 등록일
	 */
	private Date dcntRegDate;
	/*
	 * 상품 할인 수정자
	 */
	private String dcntModId;
	/*
	 * 상품 할인 수정일
	 */
	private Date dcntModDate;
	
	private String dcntStartDateStr;
	private String dcntEndDateStr;
	private List<Product> productList;
	private int prdtDcntOption;			// 1: 미등록, 2: 전체등록 
	
	public int getDcntSeq() {
		return dcntSeq;
	}
	public void setDcntSeq(int dcntSeq) {
		this.dcntSeq = dcntSeq;
	}
	public String getDcntName() {
		return dcntName;
	}
	public void setDcntName(String dcntName) {
		this.dcntName = dcntName;
	}
	public int getDcntPrice() {
		return dcntPrice;
	}
	public void setDcntPrice(int dcntPrice) {
		this.dcntPrice = dcntPrice;
	}
	public String getDcntType() {
		return dcntType;
	}
	public void setDcntType(String dcntType) {
		this.dcntType = dcntType;
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
	public String getDcntRegId() {
		return dcntRegId;
	}
	public void setDcntRegId(String dcntRegId) {
		this.dcntRegId = dcntRegId;
	}
	public Date getDcntRegDate() {
		return dcntRegDate;
	}
	public void setDcntRegDate(Date dcntRegDate) {
		this.dcntRegDate = dcntRegDate;
	}
	public String getDcntModId() {
		return dcntModId;
	}
	public void setDcntModId(String dcntModId) {
		this.dcntModId = dcntModId;
	}
	public Date getDcntModDate() {
		return dcntModDate;
	}
	public void setDcntModDate(Date dcntModDate) {
		this.dcntModDate = dcntModDate;
	}
	public String getDcntStartDateStr() {
		return dcntStartDateStr;
	}
	public void setDcntStartDateStr(String dcntStartDateStr) {
		this.dcntStartDateStr = dcntStartDateStr;
	}
	public String getDcntEndDateStr() {
		return dcntEndDateStr;
	}
	public void setDcntEndDateStr(String dcntEndDateStr) {
		this.dcntEndDateStr = dcntEndDateStr;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public int getPrdtDcntOption() {
		return prdtDcntOption;
	}
	public void setPrdtDcntOption(int prdtDcntOption) {
		this.prdtDcntOption = prdtDcntOption;
	}

}
