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
     * @param Product
     * @return
     */
	List<Product> getProductList(Product product);

    /**
     * 상품정보 등록
     * @param Product
     * @return
     */
	int createProduct(Product product);
	
    /**
     * 상품정보 
     * @param String prdtCode
     * @return
     */
	Product selectProductInfo(String prdtCode);
	
    /**
     * 상품정보 수정
     * @param Product
     * @return
     */
	int updateProduct(Product product);
    
}
