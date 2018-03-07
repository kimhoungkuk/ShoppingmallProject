package com.project.shop.backoffice.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.model.Category;
import com.project.shop.service.CategoryService;

/**
 * @author ParkSY
 * 카테고리 컨트롤러
 * 
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/list")
	public ModelAndView list(Model model) throws Exception {
		
		logger.info("CategoryController > list");
		
		ArrayList<Category> categoryList = categoryService.selectCategoryList();
		model.addAttribute("categoryList", categoryList);
		
		return new ModelAndView("backoffice/category/list");
	}
	
	@RequestMapping(value="/register")
	@ResponseBody
	public HashMap<String, String> register(@ModelAttribute Category category, HttpServletResponse response) throws Exception {
		logger.info("CategoryController > register");
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String resultYn = "";
		String resultMsg = "";
		
		try {
			resultYn = "Y";
			resultMsg = "SUCCESS";
		} catch (Exception e) {
			resultYn = "N";
			resultMsg = "처리 중 오류가 발생하였습니다.";
		}
		
		resultMap.put("ctgrId", category.getCtgrName() + "1234");
		resultMap.put("ctgrName", category.getCtgrName());
		resultMap.put("resultYn", resultYn);
		resultMap.put("resultMsg", resultMsg);

		return resultMap;
	}
	
	@RequestMapping(value="/update/{ctgrId}")
	public HashMap<String, String> udpate(@ModelAttribute Category category, HttpServletResponse response) throws Exception {
		logger.info("CategoryController > update");
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String resultYn = "";
		String resultMsg = "";
		
		try {
			resultYn = "Y";
			resultMsg = "SUCCESS";
			
			// TODO :: UPDATE CATEGORY
			
		} catch (Exception e) {
			resultYn = "N";
			resultMsg = "처리 중 오류가 발생하였습니다.";
		}
		
		resultMap.put("categoryId", category.getCtgrName() + " UPDATE " + System.currentTimeMillis());
		resultMap.put("categoryName", category.getCtgrName() + " UPDATE " + System.currentTimeMillis());
		resultMap.put("resultYn", resultYn);
		resultMap.put("resultMsg", resultMsg);

		return resultMap;
	}

}
