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

/**
 * 상품 관리 컨트롤러
 * @author kimhk0
 *
 */
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

    	return new ModelAndView("backoffice/product/productList");
    	
    }
    
    /**
     * 상품 등록.
     */
    @RequestMapping(value = "/admin/productRegister")
    public ModelAndView productForm(Model model) throws SQLException{

    	return new ModelAndView("backoffice/product/productRegister");
    	
    }

}
