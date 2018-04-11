package com.project.shop.model;

import java.util.Date;

import com.project.shop.common.PageVO;

/**
 * 상품 정보 
 * @author kimhk0 
 *
 */
public class Product extends PageVO{

	/**
	 * 상품 코드
	 */
	private String prdtCode;
	/**
	 * 상품 한글명
	 */
	private String prdtKorName;
	/**
	 * 상품 영문명
	 */
	private String prdtEngName;
	/**
	 * 상품 판매가
	 */
	private int prdtSellPrice;
	/**
	 * 상품 브랜드 아이디
	 */
	private String prdtBrandId;
	/**
	 * 상품 전시여부
	 */
	private String prdtDispYn;
	/**
	 * 상품 상세 
	 */
	private String prdtDetail;	
	/**
	 * 상품 등록자
	 */
	private String prdtRegId;
	/**
	 * 상품 등록일
	 */
	private Date prdtRegDt;
	/**
	 * 상품 수정자
	 */
	private String prdtModId;
	/**
	 * 상품 수정일
	 */
	private Date prdtModDt;
	
	public String getPrdtCode() {
		return prdtCode;
	}
	public void setPrdtCode(String prdtCode) {
		this.prdtCode = prdtCode;
	}
	
	public String getPrdtKorName() {
		return prdtKorName;
	}
	public void setPrdtKorName(String prdtKorName) {
		this.prdtKorName = prdtKorName;
	}
	
	public String getPrdtEngName() {
		return prdtEngName;
	}
	public void setPrdtEngName(String prdtEngName) {
		this.prdtEngName = prdtEngName;
	}
		
	public int getPrdtSellPrice() {
		return prdtSellPrice;
	}
	public void setPrdtSellPrice(int prdtSellPrice) {
		this.prdtSellPrice = prdtSellPrice;
	}
	
	public String getPrdtBrandId() {
		return prdtBrandId;
	}
	public void setPrdtBrandId(String prdtBrandId) {
		this.prdtBrandId = prdtBrandId;
	}
	
	public String getPrdtDispYn() {
		return prdtDispYn;
	}
	public void setPrdtDispYn(String prdtDispYn) {
		this.prdtDispYn = prdtDispYn;
	}
	
	public String getPrdtDetail() {
		return prdtDetail;
	}
	public void setPrdtDetail(String prdtDetail) {
		this.prdtDetail = prdtDetail;
	}
	
	public String getPrdtRegId() {
		return prdtRegId;
	}
	public void setPrdtRegId(String prdtRegId) {
		this.prdtRegId = prdtRegId;
	}
	
	public Date getPrdtRegDt() {
		return prdtRegDt;
	}
	public void setPrdtRegDt(Date prdtRegDt) {
		this.prdtRegDt = prdtRegDt;
	}
	
	public String getPrdtModId() {
		return prdtModId;
	}
	public void setPrdtModId(String prdtModId) {
		this.prdtModId = prdtModId;
	}
	
	public Date getPrdtModDt() {
		return prdtModDt;
	}
	public void setPrdtModDt(Date prdtModDt) {
		this.prdtModDt = prdtModDt;
	}

}
