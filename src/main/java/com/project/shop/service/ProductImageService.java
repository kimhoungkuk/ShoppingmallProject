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
	ModelAndView getProductImageList(String num);
	
	/**
	 * 상품이미지 등록
	 * @param list
	 */
	void insertProductImage(HttpServletRequest request,List<MultipartFile> mFiles);
	void insertProductImage2(HttpServletRequest request,List<MultipartFile> mFiles);
	
	
	
	
 
 }
