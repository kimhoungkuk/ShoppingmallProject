package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Product;
import com.project.shop.model.ProductImage;

/**
 * @author lmk
 *
 */
public interface ProductImageDao{
	

	/**
	 * 상품 이미지 목록
	 * @param num
	 * @return
	 */
	public List<ProductImage> selectProductImageList(String num);
	

	/**상품 이미지 등록
	 * @param dto
	 */
	public void insertProductImageDao(ProductImage dto);
	
}
