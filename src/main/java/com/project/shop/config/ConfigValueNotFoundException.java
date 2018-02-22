package com.project.shop.config;

/**
 *
 * 클래스명: <code>ConfigValueNotFoundException</code>
 * 
 * <pre>
 * Config Value Not Found Exception
 * </pre>
 *
 * @date 2018. 02. 22.
 * @author kimhk0
 *
 */
@SuppressWarnings("serial")
public class ConfigValueNotFoundException extends RuntimeException {

   public ConfigValueNotFoundException(String s) {
       super(s);
   }

   public ConfigValueNotFoundException(Exception e) {
       super(e);
   }
}