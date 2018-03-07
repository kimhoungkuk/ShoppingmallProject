package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.service.ProductService;
import com.project.shop.model.Product;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/admin/product/productList")
    public ModelAndView productList(Model model, Product product, HttpServletRequest request) throws SQLException{
    	
    	product.setTotalCount(productService.getProductListTotalCount()); 
    	product.setPagingUrl(request);
    	
    	List<Product>  productList = productService.getProductList(product);

    	model.addAttribute("productList", productList);
    	model.addAttribute("product", product);

    	return new ModelAndView("backoffice/product/productList");
    	
    }
    
    /**
     * 상품 등록폼.
     */
    @RequestMapping(value = "/admin/product/productRegisterForm")
    public ModelAndView productRegisterForm(Model model) throws SQLException{

    	return new ModelAndView("backoffice/product/productRegisterForm");
    	
    }
    
    /**
     * 상품 등록.
     */
    @RequestMapping(value = "/admin/product/productRegister",method=RequestMethod.POST)
    public String productRegister(Product product) throws SQLException{
    	
    	int num = productService.createProduct(product);

        if(num < 1){
            throw new RuntimeException("상품 등록시 오류 발생했습니다.");
        }
        
    	return ("redirect:/admin/productList");
    	
    }

}
