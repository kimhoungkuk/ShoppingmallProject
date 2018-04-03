package com.project.shop.backoffice.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.shop.model.Discount;
import com.project.shop.model.Product;
import com.project.shop.service.DiscountService;
import com.project.shop.service.ProductService;
import com.project.shop.utils.ExcelReadUtil;

/**
 * 상품할인 컨트롤러
 */
@Controller
@RequestMapping("/admin/discount")
public class DiscountController {

	private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);

	@Autowired
	private DiscountService discountService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ExcelReadUtil excelReadUtil;

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
	public ModelAndView discountRegisterForm(Model model, Product product, HttpServletRequest request)
			throws SQLException {
		// 리스트 총 카운트 및 페이징 처리
		product.setTotalCount(productService.getProductListTotalCount());
		// 리스트 페이징 URL 처리
		product.setPagingUrl(request);

		List<Product> productList = productService.getProductList(product);

		model.addAttribute("productList", productList);
		model.addAttribute("product", product);

		return new ModelAndView("backoffice/discount/discountRegisterForm");

	}

	/**
	 * 상품 할인 등록폼.
	 */
	@RequestMapping(value = "/discountRegister", method = RequestMethod.POST)
	public String discountRegister(Discount discount, HttpServletRequest request) throws SQLException {
		int num = discountService.createDiscount(discount);

		// 상품리스트 등록
		//discountService.createProductDiscount(discount);

		if (num < 1) {
			throw new RuntimeException("상품 할인 등록시 오류 발생했습니다.");
		}

		return ("redirect:/admin/discount/discountList");

	}

	/**
	 * 상품 할인 수정폼.
	 */
	@RequestMapping(value = "/discountModifyForm/{dcntSeq}")
	public ModelAndView discountModifyForm(@PathVariable int dcntSeq, Model model, Product product,
			HttpServletRequest request) throws SQLException {

		Discount discount = discountService.selectDiscountInfo(dcntSeq);

		model.addAttribute("discount", discount);

		return new ModelAndView("backoffice/discount/discountModifyForm");

	}

	/**
	 * 상품 할인 수정.
	 */
	@RequestMapping(value = "/discountModify", method = RequestMethod.POST)
	public String discountModify(Discount discount) throws SQLException {

		int num = discountService.updateDiscount(discount);

		if (num < 1) {
			throw new RuntimeException("상품 할인 등록 오류가 발생했습니다.");
		}

		return ("redirect:/admin/discount/discountModifyForm/" + discount.getDcntSeq());

	}

	/**
	 * 할인 상품 등록 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveFile.ajax", method = RequestMethod.POST)
	public String saveFile(@RequestParam int dcntSeq, MultipartHttpServletRequest request) throws SQLException {
		
		logger.info("dcntSeq : "+dcntSeq);
		Iterator<String> itr = request.getFileNames();
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			
			logger.info(mpf.getOriginalFilename() + " uploaded!");
			try {
				logger.info("file length : " + mpf.getBytes().length);
				logger.info("file name : " + mpf.getOriginalFilename());
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			List<String> codeList = excelReadUtil.readExcelFile(mpf);
			discountService.saveProductDisCountList(codeList, dcntSeq);
			return "success";
		} else {
			return "failed";
		}

	}

}
