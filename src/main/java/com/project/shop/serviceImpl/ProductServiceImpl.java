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
     * 상품목록 총 카운트
     * @param criteria
     * @return
     */
    @Override
	public int getProductListTotalCount(){
   		return this.productDao.getProductListTotalCount();
	}
 
    /**
     * 상품목록 
     * @param criteria
     * @return
     */
    @Override
	public List<Product> getProductList(Product product){
   		return this.productDao.selectProductList(product);
	}
    
    /**
     * 상품등록
     * @param Product
     * @return
     */
    @Override
	public int createProduct(Product product){
    	try
    	{
    		return this.productDao.createProduct(product);
    	}catch(Exception e){
    		log.error(e.getMessage());
    		throw new RuntimeException(e.getMessage());
    	}
	}
    
    /**
     * 상품정보 
     * @param String prdtCode
     * @return
     */
    @Override
	public Product selectProductInfo(String prdtCode){
    	try
    	{
    		return this.productDao.selectProductInfo(prdtCode);
    	}catch(Exception e){
    		log.error(e.getMessage());
    		throw new RuntimeException(e.getMessage());
    	}
	}

    /**
     * 상품수정
     * @param Product
     * @return
     */
    @Override
	public int updateProduct(Product product){
    	try
    	{
    		return this.productDao.updateProduct(product);
    	}catch(Exception e){
    		log.error(e.getMessage());
    		throw new RuntimeException(e.getMessage());
    	}
	}
    
}
