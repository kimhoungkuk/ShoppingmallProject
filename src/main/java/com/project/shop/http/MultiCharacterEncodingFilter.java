package com.project.shop.http;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.CharacterEncodingFilter;

public class MultiCharacterEncodingFilter extends CharacterEncodingFilter {

    private String eucEncoding;
    private String eucUrl1;
    private String eucUrl2;
    private String eucUrl3;
    private String eucUrl4;
    private String eucUrl5;
    private String eucUrl6;
    private String eucUrl7;
    private String eucUrl8;
    private String eucUrl9;
    private String eucUrl10;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        if (StringUtils.equals(requestUri, eucUrl1)
                || StringUtils.equals(requestUri, eucUrl2)
                || StringUtils.equals(requestUri, eucUrl3)
                || StringUtils.equals(requestUri, eucUrl4)
                || StringUtils.equals(requestUri, eucUrl5)
                || StringUtils.equals(requestUri, eucUrl6)
                || StringUtils.equals(requestUri, eucUrl7)
                || StringUtils.equals(requestUri, eucUrl8)
                || StringUtils.equals(requestUri, eucUrl9)
                || StringUtils.equals(requestUri, eucUrl10)) {

            System.out.println("euc encoding character set : " + this.eucEncoding);

            request.setCharacterEncoding(this.eucEncoding);

            filterChain.doFilter(request, response);
        } else {
            super.doFilterInternal(request, response, filterChain);
        }
    }

    /**
     * @param eucEncoding the eucEncoding to set
     */
    public void setEucEncoding(String eucEncoding) {
        this.eucEncoding = eucEncoding;
    }

    /**
     * @param eucUrl1 the eucUrl1 to set
     */
    public void setEucUrl1(String eucUrl1) {
        this.eucUrl1 = eucUrl1;
    }

    /**
     * @param eucUrl2 the eucUrl2 to set
     */
    public void setEucUrl2(String eucUrl2) {
        this.eucUrl2 = eucUrl2;
    }

    /**
     * @param eucUrl3 the eucUrl3 to set
     */
    public void setEucUrl3(String eucUrl3) {
        this.eucUrl3 = eucUrl3;
    }

    /**
     * @param eucUrl4 the eucUrl4 to set
     */
    public void setEucUrl4(String eucUrl4) {
        this.eucUrl4 = eucUrl4;
    }

    /**
     * @param eucUrl5 the eucUrl5 to set
     */
    public void setEucUrl5(String eucUrl5) {
        this.eucUrl5 = eucUrl5;
    }

    /**
     * @param eucUrl6 the eucUrl6 to set
     */
    public void setEucUrl6(String eucUrl6) {
        this.eucUrl6 = eucUrl6;
    }

    /**
     * @param eucUrl7 the eucUrl7 to set
     */
    public void setEucUrl7(String eucUrl7) {
        this.eucUrl7 = eucUrl7;
    }

    /**
     * @param eucUrl8 the eucUrl8 to set
     */
    public void setEucUrl8(String eucUrl8) {
        this.eucUrl8 = eucUrl8;
    }

    /**
     * @param eucUrl9 the eucUrl9 to set
     */
    public void setEucUrl9(String eucUrl9) {
        this.eucUrl9 = eucUrl9;
    }

    /**
     * @param eucUrl10 the eucUrl10 to set
     */
    public void setEucUrl10(String eucUrl10) {
        this.eucUrl10 = eucUrl10;
    }
}
