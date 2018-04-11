package com.project.shop.dao;

import java.util.List;

import com.project.shop.model.Product;

public interface ProductDao{

    /**
     *  상품목록 총 카운트
     * @param Product
     * @return
     */
	public int getProductListTotalCount();
	
    /**
     * 상품목록 
     * @param criteria
     * @return
     */
	public List<Product> selectProductList(Product product);
	
	/**
	 * 상품코드 목록 
	 * @return
	 */
	List<String> selectProductCodeList();

    /**
     * 상품등록
     * @param Product
     * @return
     */
	public int createProduct(Product product);
	
    /**
     * 상품정보
     * @param String prdtCode
     * @return
     */
	public Product selectProductInfo(String prdtCode);
	
    /**
     * 상품수정
     * @param Product
     * @return
     */
	public int updateProduct(Product product);
	
}
