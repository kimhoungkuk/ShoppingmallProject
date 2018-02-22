package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.service.ProductService;
import com.project.shop.config.Config;
import com.project.shop.model.Product;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller 
public class ProductController {

    private Log log = LogFactory.getLog(this.getClass());
    
    @Autowired
    private ProductService productService;

    /**
     * 상품 리스트.
     */
    @RequestMapping(value = "/admin/product")
    public ModelAndView productList(ModelMap modelMap) throws SQLException{
    	
    	List<Product>  product = productService.getProductList();

    	log.debug(product);
    	
    	System.out.println(Config.getString("hello"));
    	
    	return new ModelAndView("home");
    }

}
