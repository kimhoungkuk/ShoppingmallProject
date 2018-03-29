package com.project.shop.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 * 클래스명: <code>Util</code>
 * 
 * <pre>
 * 필요할때마다 그때그때 만듬
 * http패키지에서 사용하기위해만듬
 * 잡다한 기능제거
 * 
 * QoomSessionServer를 이용할때 사용
 * </pre>
 * 
 * @date 2018. 02. 23.
 * @author kimhk0
 * 
 */
public class Util {

    /**
     * <pre>
     * 자바의 URI인코딩은 ' '문자열을 처리하는게 문제가 있다
     * 이를 RFC1738의 공식인 '%20'으로 변경처리한다.
     * 
     * 어디다 적을대가 없어서
     * 
     * ; %3B
     * ? %3F
     * / %2F
     * : %3A
     * # %23
     * & %24
     * = %3D
     * + %2B
     * $ %26
     * , %2C
     * ' ' -> '+' -> %20
     * % %25
     * < %3C
     * > %3E
     * ~ %7E
     * % %25
     * 
     * <pre>
     * @param url
     * @return
     */
    public static String UrlEncoding(String url, String charset) {
        return ToRfc1738Encode(url, charset);
    }

    /**
     * 자바에서는 UrlEncoding시에 ' '을 '+'로 바꾼다. 해당유알엘은 실제로 가져올수없다. 그래서 '+'를 추가로
     * '%20'으로 바꿔준다.
     * 
     * @param url
     * @param charset
     * @return
     */
    @SuppressWarnings("deprecation")
    private static String ToRfc1738Encode(String url, String charset) {
        String encodedString = "";
        try {
            if (charset != null) {
                encodedString = URLEncoder.encode(url, charset);
            } else {
                encodedString = URLEncoder.encode(url);
            }
        } catch (UnsupportedEncodingException e) {
            encodedString = URLEncoder.encode(url);
        }

        StringBuilder buffer = new StringBuilder(512);
        for (int i = 0; i < encodedString.length(); i++) {
            char ch = encodedString.charAt(i);
            if (ch == '+') {
                buffer.append("%20");
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    public static String UrlEncoding(String url) {
        return ToRfc1738Encode(url, null);
    }

    @SuppressWarnings("deprecation")
    public static String UrlDecoding(String url, String charset) {
        try {
            return URLDecoder.decode(url, charset);
        } catch (UnsupportedEncodingException e) {
            return URLDecoder.decode(url);
        }
    }

    @SuppressWarnings("deprecation")
    public static String UrlDecoding(String url) {
        return URLDecoder.decode(url);
    }

    /**
     * <pre>
     * httpResponse해더에서 contentType을 분석해서 리턴한다.
     * </pre>
     * 
     * @param contentType
     * @return
     */
    public static String getContentType(String contentType) {
        if (contentType == null) {
            return null;
        }

        String replaceContentType = "";
        int index = StringUtils.indexOf(contentType, ';');
        if (index != -1) {
            replaceContentType = StringUtils.substring(contentType, 0, index);
        } else {
            replaceContentType = contentType;
        }

        return replaceContentType;
    }
}