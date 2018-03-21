package com.project.shop.backoffice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		/*String selecttest= productOptionDao.selectProductOption();
		model.addAttribute("test", selecttest);*/
	/*	int a;
		for(a = 0 ; a < list.size() ; a++);
		 String[] testset = list.toArray(new String[list.size()]);*/

		
		
		/*String test = Arrays.deepToString(testset);
		logger.debug(test);*/
	
		
		model.addAttribute("productOptionList",list);
		
		
		
		//logger.info(Arrays.deepToString(list.get(0)));
		//Map<String, Object> list2 =  productOptionService.selectarray();
		//logger.info("리스트에서" +list.get(4).toString()); //com.머시기
		//logger.info("리스트에서 어레이"+(list.get(5)));
		
		//logger.info("투스트링"+dto.getPrdtColorCode().toString());
		//logger.info("어레이투차"+ Arrays.deepToString(dto.getPrdtColorCode())); //null
		//logger.info("다른거"+ dto.getColorName());
		
		
		
		/*Map<String,Object> map = productOptionService.getProductOptionList(dto);
		map.get(dto.getPrdtColorCode());*/
		//List<ProductOption> list3 = new ArrayList(list2.values());
		
		//model.addAttribute("list2",list2);
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
		
//		String test = Arrays.deepToString(request.getParameterValues("prdtColorCode"));
//		logger.info(test); //[1 , 2] []이게붙음

		/*Map<String, Object> test = new HashMap<String, Object>();
		String[] test2 = request.getParameterValues("prdtColorCode");*/
		
		
		
//		//logger.info(test2); list 라 안됨
	/*	int a;
		for(a=0 ; a<test2.length ; a++);*/
//			System.out.println(test2[a]);
//
//		
//			String arrayset = StringUtils.join(test2,"','");
//			logger.info("'"+arrayset+"'"); // ('red,blak') 하나로나옴 
//			String arraysets = "'"+arrayset+"'";
//		//logger.info(test2[a]); //이거는 에러
//		//시이버어어어어어러어랸어 다안돼 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
//		
//		String test3 = request.getParameterValues("prdtColorCode").toString();
//		logger.info(test3); //이상한문자
//		
//		String test4 = Arrays.toString(request.getParameterValues("prdtColorCode"));
//		logger.info(test4);//첨꺼랑같음
//		
//		logger.info(test2[a]);
		
		
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
