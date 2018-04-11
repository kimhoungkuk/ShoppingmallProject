package com.project.shop.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
*
* @author kimhk0
*/
public class BindingInitializer implements WebBindingInitializer {

   @Override
   public void initBinder(WebDataBinder binder, WebRequest request) {
       binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
       binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
   }
}