package com.project.shop.backoffice.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.shop.model.ProductOption;
import com.project.shop.service.ProductOptionService;

@Controller
public class ProductOptionController {
	
	
/**	
  * 상품 옵션 관리 컨트롤러 
  *	@author SieunLim 
  *
  */

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductOptionService productOptionService;
	

	
//List
	//이동+기능 : 상품 옵션 리스트
	@RequestMapping(value="/admin/productOptionList")
	public String ProductOptionList(Model model) {
		
		List<ProductOption> list = productOptionService.getProductOptionList();
	
		
		model.addAttribute("productOptionList",list);
		
		return "backoffice/productOption/productOptionList";
		
	}
	
	
//Register
	
	// 이동 : 상품 옵션 등록 폼
	@RequestMapping(value="/admin/porForm")
	public String PorForm(Model model) {
		return "backoffice/productOption/productOptionRegister";
	}
	
	
	// 기능 : 상품 옵션 등록
	@RequestMapping(value="/admin/productOptionRegister" , method=RequestMethod.POST)
	public String ProductOptionRegister(HttpServletRequest request, Model model, ProductOption dto) {
		
		
		dto.setPrdtCode(request.getParameter("prdtCode"));
		//dto.setPrdtColorCode(test2);
		dto.setPrdtColorCode(request.getParameter("prdtColorCode"));
		dto.setColorName(request.getParameter("colorName"));
		dto.setPrdtSize(request.getParameter("prdtSize"));
		dto.setPrdtLaveCount(Integer.parseInt(request.getParameter("prdtLaveCount")));
		
		
		
		
		productOptionService.registerProductOption(dto);
		
	
		return ("redirect:/admin/productOptionList");
	}
	
	
//Modify
	
	// 이동 & 기능: 상품 옵션 수정 폼과 수정 리스트 가져오기
	@RequestMapping(value="/admin/pomForm/{modifydelete}", method=RequestMethod.GET)
	public String PomForm(@PathVariable("modifydelete") String prdtCode,HttpServletRequest request, Model model) {
		
		
		
		ProductOption dto = productOptionService.getProductOptionModify(prdtCode);
		
		model.addAttribute("modify",dto);
		
		return "backoffice/productOption/productOptionModify";
	}
	
	// 기능 : 상품 옵션 수정 
	@RequestMapping(value="/admin/productOptionModify" , method=RequestMethod.POST)
	public String ProductOptionModify(HttpServletRequest request, Model model, ProductOption dto) {
		
		dto.setPrdtCode(request.getParameter("prdtCode"));
		dto.setPrdtColorCode(request.getParameter("prdtColorCode"));
		//dto.setPrdtColorCode(test2);
		dto.setColorName(request.getParameter("colorName"));
		dto.setPrdtSize(request.getParameter("prdtSize"));
		dto.setPrdtLaveCount(Integer.parseInt(request.getParameter("prdtLaveCount")));
		dto.setModId(request.getParameter("modId"));
		
		productOptionService.updateProductOption(dto);
		
		return ("redirect:/admin/productOptionList");
	}
	
	
	
}
