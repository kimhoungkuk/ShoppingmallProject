package com.project.shop.backoffice.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.config.Config;
import com.project.shop.model.Product;
import com.project.shop.service.ProductService;

@Controller
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
    @Autowired
    private ProductService productService;

    /**
     * 상품 리스트.
     */
    @RequestMapping(value = "/admin/product")
    public ModelAndView productList(ModelMap modelMap) throws SQLException{
    	
    	LOGGER.info("hello SLF4J");
    	
    	List<Product>  product = productService.getProductList();

    	Product product2 = product.get(0);
    	
    	System.out.println(Config.getString("hello"));
    	
    	return new ModelAndView("home");
    }

}
