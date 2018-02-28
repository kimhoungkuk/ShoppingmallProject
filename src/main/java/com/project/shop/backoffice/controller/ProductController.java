package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.service.ProductService;
import com.project.shop.model.Product;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller 
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductService productService; 

    /**
     * 상품 리스트.
     */
    @RequestMapping(value = "/admin/productList")
    public ModelAndView productList(Model model) throws SQLException{
    	
    	List<Product>  productList = productService.getProductList();

    	model.addAttribute("productList", productList);
    	
    	logger.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa : " + productList.get(0).getProductName());
    	
    	return new ModelAndView("backoffice/product/productList");
    }

}
