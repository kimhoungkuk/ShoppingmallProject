package com.project.shop.service;

import java.util.List;

import com.project.shop.model.Product;

/**
 * 상품정보
 * @author kimhk0
 *
 */
public interface ProductService {

    /**
     * 상품목록 총 카운트
     * @param criteria
     * @return
     */
	int getProductListTotalCount();

    /**
     * 상품목록 
     * @param criteria
     * @return
     */
	List<Product> getProductList(Product product);

    /**
     * 상품정보 등록
     * @param Product
     * @return
     */
	int createProduct(Product product);
    
}
