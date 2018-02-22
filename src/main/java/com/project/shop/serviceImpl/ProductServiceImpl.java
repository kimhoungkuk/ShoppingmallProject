package com.project.shop.serviceImpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.service.ProductService;
import com.project.shop.dao.ProductDao;
import com.project.shop.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
    Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private ProductDao productDao;
    
    /**
     * 상품목록 
     * @param criteria
     * @return
     */
    @Override
	public List<Product> getProductList(){
   		return this.productDao.selectProductList();
	}

}
