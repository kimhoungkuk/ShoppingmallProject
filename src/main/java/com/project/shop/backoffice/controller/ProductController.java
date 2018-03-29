package com.project.shop.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.service.CategoryService;
import com.project.shop.service.ProductImageService;
import com.project.shop.service.ProductService;
import com.project.shop.model.Category;
import com.project.shop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
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
    
    @Autowired
    private CategoryService categoryService;
	
    @Autowired
    private ProductImageService productImageService;
 
    /**
     * 상품 리스트.
     */
    @RequestMapping(value = "/admin/product/productList")
    public ModelAndView productList(Model model, Product product, HttpServletRequest request) throws SQLException{
    	
    	// 리스트 총 카운트  및 페이징 처리
    	product.setTotalCount(productService.getProductListTotalCount()); 
    	// 리스트 페이징 URL 처리 
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

		ArrayList<Category> categoryList = categoryService.selectCategoryList();
		model.addAttribute("categoryList", categoryList);
		
    	return new ModelAndView("backoffice/product/productRegisterForm");
    	
    }
    
    /**
     * 상품 등록.
     */
    @RequestMapping(value = "/admin/product/productRegister",method=RequestMethod.POST)
    public String productRegister(Product product , HttpServletRequest request) throws SQLException{
    	
    	int num = productService.createProduct(product);
    	
    	String prdtCode = product.getPrdtCode();

  		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드
  		
  		//대표이미지 리스트
  		List<MultipartFile> mFiles = multipartRequest.getFiles("input_img");
  		
  		//상세이미지 혹은 대표이미지의 캐러셀 이미지 리스트
  		List<MultipartFile> mFiles2 = multipartRequest.getFiles("input_imgs");
  		
  		productImageService.ProductImageInsert(request, mFiles);  		
  		productImageService.ProductImageInsert2(request, mFiles2);
  		
        if(num < 1){
            throw new RuntimeException("상품 등록시 오류 발생했습니다.");
        }
        
    	return ("redirect:/admin/product/productList");
    	
    }
    
    /**
     * 상품 수정폼.
     */
    @RequestMapping(value = "/admin/product/productModify/{prdtCode}")
    public ModelAndView productModifyForm(@PathVariable String prdtCode, Model model) throws SQLException{

    	Product product = productService.selectProductInfo(prdtCode);
    	
    	model.addAttribute("product",product);
    	
    	return new ModelAndView("backoffice/product/productModifyForm");
    	
    }

    /**
     * 상품 수정.
     */
    @RequestMapping(value = "/admin/product/productModify", method=RequestMethod.POST)
    public String productModify(Product product) throws SQLException{

    	int num = productService.updateProduct(product);

        if(num < 1){
            throw new RuntimeException("상품 등록시 오류 발생했습니다.");
        }
        
    	return ("redirect:/admin/product/productModify/"+product.getPrdtCode());
    	
    }
    
}
