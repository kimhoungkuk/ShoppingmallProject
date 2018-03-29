package com.project.shop.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
//상품 이미지 관리
public class ProductImage {
	//상품코드
	private String prdtCode;
	//이미지 코드
	private String prdtImageCode;
	//이미지 url
	private String prdtImagePath;
	//등록 날짜
	private Date regDtm;
	//원본 이름
	private String prdtOrgImageName;
	//저장 이름
	private String prdtSaveImageName;
	//스프링에서 멀티파일 업로드 위해
	private MultipartFile file;
	
	public String getPrdtCode() {
		return prdtCode;
	}
	public void setPrdtCode(String prdtCode) {
		this.prdtCode = prdtCode;
	}
	public String getPrdtImageCode() {
		return prdtImageCode;
	}
	public void setPrdtImageCode(String prdtImageCode) {
		this.prdtImageCode = prdtImageCode;
	}
	public String getPrdtImagePath() {
		return prdtImagePath;
	}
	public void setPrdtImagePath(String prdtImagePath) {
		this.prdtImagePath = prdtImagePath;
	}
	public Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}
	public String getPrdtOrgImageName() {
		return prdtOrgImageName;
	}
	public void setPrdtOrgImageName(String prdtOrgImageName) {
		this.prdtOrgImageName = prdtOrgImageName;
	}
	public String getPrdtSaveImageName() {
		return prdtSaveImageName;
	}
	public void setPrdtSaveImageName(String prdtSaveImageName) {
		this.prdtSaveImageName = prdtSaveImageName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}