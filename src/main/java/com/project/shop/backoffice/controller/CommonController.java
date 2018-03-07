package com.project.shop.backoffice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 공통 페이징 관리
 * @author kimhk0
 *
 */
@Controller 
public class CommonController {

    /**
     * 게시판 공통 페이징 
     */
    @RequestMapping(value = "/admin/common/paging")
    public ModelAndView productList(Model model, HttpServletRequest req) throws SQLException{
    	return new ModelAndView("common/backoffice_paging");    	
    }
    
}
