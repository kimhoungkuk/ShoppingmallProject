package com.project.shop.model;

public class Product {

	private int productSeq;
	private String productName;
	private int productPrice;
	private String productContents;
	
	public int getProductSeq() {
		return productSeq;
	}
	
	public void setProductSeq(int productSeq) {
		this.productSeq = productSeq;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getProductContents() {
		return productContents;
	}
	
	public void setProductContents(String productContents) {
		this.productContents = productContents;
	}
	
}
