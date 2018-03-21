package com.project.shop.service;

import java.util.List;
import java.util.Map;

import com.project.shop.model.ProductOption;

public interface ProductOptionService {

	void registerProductOption(ProductOption dto);
	
	List<ProductOption> getProductOptionList();
	
	ProductOption getProductOptionModify(String prdtCode);
	
	void updateProductOption(ProductOption dto);
	
}
