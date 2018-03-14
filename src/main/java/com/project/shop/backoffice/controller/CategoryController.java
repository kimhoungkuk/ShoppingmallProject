package com.project.shop.backoffice.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.model.Category;
import com.project.shop.service.CategoryService;

/**
 * @author ParkSY 카테고리 컨트롤러
 * 
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Model model) throws Exception {

		logger.info("CategoryController > list");

		ArrayList<Category> categoryList = categoryService.selectCategoryList();
		model.addAttribute("categoryList", categoryList);

		return new ModelAndView("backoffice/category/list");
	}

	@RequestMapping(value = "/register")
	@ResponseBody
	public HashMap<String, String> register(@ModelAttribute Category category, HttpServletResponse response)
			throws Exception {
		logger.info("CategoryController > register");

		HashMap<String, String> resultMap = new HashMap<String, String>();
		String resultYn = "";
		String resultMsg = "";
		
		try {
			
			if (category == null || StringUtils.isEmpty(StringUtils.trimWhitespace(category.getCtgrName()))) {
				resultYn = "N";
				resultMsg = "카테고리 정보를 입력해 주세요.";
				
			} else {
				// insert category
				categoryService.insertCategory(category);
				
				// get category
				if (category.getCtgrSeq() > 0) {
					category = categoryService.getCategory(category);
				}
				
				resultMap.put("ctgrId", category.getCtgrId());
				resultMap.put("ctgrName", category.getCtgrName());
				
				resultYn = "Y";
				resultMsg = "SUCCESS";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resultYn = "N";
			resultMsg = "처리 중 오류가 발생하였습니다.";
		}

		resultMap.put("resultYn", resultYn);
		resultMap.put("resultMsg", resultMsg);

		return resultMap;
	}

	@RequestMapping(value = "/update/{type}/{ctgrId}")
	@ResponseBody
	public HashMap<String, String> udpate(@PathVariable String type
											, @PathVariable String ctgrId
											, @ModelAttribute Category category
											, HttpServletResponse response) throws Exception {
		
		logger.info("CategoryController > update");

		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		String resultYn = "";
		String resultMsg = "";
		
		int result = 0;

		try {
			
			if (StringUtils.isEmpty(type) || StringUtils.isEmpty(StringUtils.trimAllWhitespace(ctgrId)) || category == null) {
				resultYn = "N";
				resultMsg = "처리 중 오류가 발생하였습니다.";
				
			} else {
				
				if ("dispOrder".equals(type)) {
					if (StringUtils.isEmpty(StringUtils.trimAllWhitespace(category.getPrntCtgrId()))
													|| category.getDispOrder() == category.getDestDispOrder()) {
						resultYn = "N";
						resultMsg = "상위카테고리 정보 또는 노출 순서 정보를 확인해 주세요.";
					}
				} else if ("rename".equals(type)) {
					if (StringUtils.isEmpty(StringUtils.trimAllWhitespace(category.getCtgrName()))) {
						resultYn = "N";
						resultMsg = "카테고리명을 확인해 주세요.";
					}
				} else if ("useYn".equals(type)) {
					if (StringUtils.isEmpty(StringUtils.trimAllWhitespace(category.getUseYn()))) {
						resultYn = "N";
						resultMsg = "카테고리 사용 유무를 확인해 주세요.";
					}
				}
				
				if (!"N".equals(resultYn)) {
					category.setCtgrId(ctgrId);
					result = categoryService.updateCategory(type, category);
					
					if (result == 0) {
						resultYn = "N";
						resultMsg = "수정 할 카테고리 정보를 찾을 수 없습니다.";
					} else {
						resultYn = "Y";
						resultMsg = "SUCCESS";
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultYn = "N";
			resultMsg = "처리 중 오류가 발생하였습니다.";
		}

		resultMap.put("resultYn", resultYn);
		resultMap.put("resultMsg", resultMsg);

		return resultMap;
	}

}
