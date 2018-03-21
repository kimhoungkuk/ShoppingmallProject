package com.project.shop.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.model.ProductImage;


/**
 * @author lmk
 *
 */
public interface ProductImageService {

    /**
     * 상품이미지 목록 
     * @param criteria
     * @return
     */
	ModelAndView productImageDetail(String prdtCode);
	
	/**
	 * 상품이미지 등록
	 * @param list
	 */
	void ProductImageInsert(HttpServletRequest request,List<MultipartFile> mFiles);
	void ProductImageInsert2(HttpServletRequest request,List<MultipartFile> mFiles);
	
	/**상품 수정
	 * @param request
	 * @param dto
	 */
	void ProductImageUpdate2(HttpServletRequest request, MultipartFile mFile, String prdtImgCode);
	void ProductImageUpdate(HttpServletRequest request, List<MultipartFile> mFiles);
	
	/**상품코드에 해당하는 이미지 삭제
	 * @param request
	 * @param prdtCode
	 */
	void ProductImageDelete(HttpServletRequest request, String prdtCode);
 
 }
