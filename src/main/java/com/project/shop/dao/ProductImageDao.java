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
	public List<ProductImage> selectProductImageList(String prdtCode);

	public List<ProductImage> selectProductImageList2(String prdtCode);
	

	/**상품 이미지 등록
	 * @param dto
	 */
	public void insertProductImageDao(ProductImage dto);
	
	/**상품 이미지 삭제
	 * @param dto
	 */
	public void deleteProductImage(ProductImage dto);
	
	/**상품코드에 해당하는 이미지 삭제
	 * @param prdtCode
	 */
	public void deleteProductImage(String prdtCode);
}
