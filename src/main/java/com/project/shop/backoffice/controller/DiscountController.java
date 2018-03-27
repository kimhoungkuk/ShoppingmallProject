package com.project.shop.backoffice.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.model.Discount;
import com.project.shop.service.DiscountService;

/**
 * 상품할인 컨트롤러
 */
@Controller
@RequestMapping("/admin/discount")
public class DiscountController {

	private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);

	@Autowired
	private DiscountService discountService;

	/**
	 * 상품 할인 리스트.
	 */
	@RequestMapping(value = "/discountList")
	public ModelAndView discountList(Model model, Discount discount, HttpServletRequest request) throws SQLException {

		discount.setTotalCount(discountService.getDiscountListTotalCount());
		discount.setPagingUrl(request);

		List<Discount> discountList = discountService.getDiscountList(discount);

		model.addAttribute("discountList", discountList);
		model.addAttribute("discount", discount);

		return new ModelAndView("backoffice/discount/discountList");

	}
	
	 /**
     * 상품 할인 등록폼.
     */
    @RequestMapping(value = "/discountRegisterForm")
    public ModelAndView discountRegisterForm(Model model) throws SQLException{

    	return new ModelAndView("backoffice/discount/discountRegisterForm");
    	
    }
    
    /**
     * 상품 할인 등록.
     */
    @RequestMapping(value = "/discountRegister",method=RequestMethod.POST)
    public String discountRegister(Discount discount, HttpServletRequest request) throws SQLException{
    	System.out.println(discount.getDcntStartDate());
    	int num = discountService.createDiscount(discount);

        if(num < 1){
            throw new RuntimeException("상품 할인 등록시 오류 발생했습니다.");
        }
        
    	return ("redirect:/admin/discount/discountList");
    	
    }

}
