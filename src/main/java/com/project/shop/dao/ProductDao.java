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
     * 상품등록
     * @param Product
     * @return
     */
	public int createProduct(Product product);
	
}
