package com.project.shop.service;

import java.util.List;
import java.util.Map;

import com.project.shop.model.ProductOption;

public interface ProductOptionService {

	void registerProductOption(List<ProductOption> list);
	
	List<ProductOption> getProductOptionList();
	
	List<ProductOption> getProductOptionModify(String modifycode);
	
	void updateProductOption(List<ProductOption> list);
	
	void deleteProductOption(String optionSeq);
	
}
