package com.project.shop.backoffice.controller;


import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String ProductOptionRegister(HttpServletRequest request, Model model) {

		String[] code = request.getParameterValues("prdtCode");
		String[] colorcode = request.getParameterValues("prdtColorCode");
		String[] colorname = request.getParameterValues("colorName");
		String[] size = request.getParameterValues("prdtSize");
		String[] count = request.getParameterValues("prdtLaveCount");
		String[] rgsid = request.getParameterValues("rgsId");

		List<ProductOption> list = new ArrayList<ProductOption>();
		
		for(int i = 0; i <= colorcode.length-1; i++) {
		 ProductOption dto = new ProductOption();
		 dto.setPrdtCode(code[i]);
		 dto.setPrdtColorCode(colorcode[i]);
		 dto.setColorName(colorname[i]);
		 dto.setPrdtSize(size[i]);
		 dto.setPrdtLaveCount(Integer.parseInt(count[i]));
		 dto.setRgsId(rgsid[i]);
		 list.add(dto);
		}
		
		productOptionService.registerProductOption(list);

		return ("redirect:/admin/productOptionList");
	}
	
	
//Modify
	
	// 이동 & 기능: 상품 옵션 수정 폼과 수정 리스트 가져오기
	@RequestMapping(value="/admin/pomForm/{modifycode}", method=RequestMethod.GET)
	public String PomForm(@PathVariable("modifycode") String modifycode,HttpServletRequest request, Model model) {
		
		List<ProductOption> dto = productOptionService.getProductOptionModify(modifycode);
		
		model.addAttribute("modify",dto);
		model.addAttribute("modifycode", modifycode);
		
		
		return "backoffice/productOption/productOptionModify";
	}
	
	// 기능 : 상품 옵션 수정 
	@RequestMapping(value="/admin/productOptionModify" , method=RequestMethod.POST)
	public String ProductOptionModify(HttpServletRequest request, Model model) {
		
		String[] code = request.getParameterValues("prdtCode");
		String[] colorcode = request.getParameterValues("prdtColorCode");
		String[] colorname = request.getParameterValues("colorName");
		String[] size = request.getParameterValues("prdtSize");
		String[] count = request.getParameterValues("prdtLaveCount");
		String[] modid = request.getParameterValues("modId");
		//수정용
//		String[] mdprdtcode = request.getParameterValues("mdprdtCode");
//		String[] mdprdtcolorcode = request.getParameterValues("mdprdtColorCode");
//		String[] mdprdtsize = request.getParameterValues("mdprdtSize");
		String[] optionseq = request.getParameterValues("optionSeq");
		
		List<ProductOption> list = new ArrayList<ProductOption>();
		
		for(int i = 0; i <= colorcode.length-1; i++) {
		 ProductOption dto = new ProductOption();
		 dto.setPrdtCode(code[i]);
		 dto.setPrdtColorCode(colorcode[i]);
		 dto.setColorName(colorname[i]);
		 dto.setPrdtSize(size[i]);
		 dto.setPrdtLaveCount(Integer.parseInt(count[i]));
		 dto.setModId(modid[i]);
		 
		 /*dto.setMdprdtCode(mdprdtcode[i]);
		 dto.setMdprdtColorCode(mdprdtcolorcode[i]);
		 dto.setMdprdtSize(mdprdtsize[i]);*/
		 dto.setOptionSeq(Integer.parseInt(optionseq[i]));
		 
		 list.add(dto);
		}
		
		productOptionService.updateProductOption(list);
		
		return ("redirect:/admin/productOptionList");
	}
	
	//Delete
	
	@RequestMapping(value="/admin/pod/{optionSeq}", method=RequestMethod.DELETE)
	@ResponseBody
	public HashMap<String, String> delete(@PathVariable("optionSeq") String optionSeq) {
		/*@PathVariable("code") String code,@PathVariable("colorcode") String colorcode,@PathVariable("size") String size
		dto.setMdprdtCode(code);
		dto.setMdprdtColorCode(colorcode);
		dto.setMdprdtSize(size);*/
		
		productOptionService.deleteProductOption(optionSeq);
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		String resultYn = "Y";
		String resultMsg = "삭제되었습니다";
		
		resultMap.put("resultYn", resultYn);
		resultMap.put("resultMsg", resultMsg);
		
		
		return resultMap;
	}
	
	
}
