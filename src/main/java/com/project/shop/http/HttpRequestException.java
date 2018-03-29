package com.project.shop.http;

/**
 * 
 * 클래스명: <code>HttpRequestException</code>
 * 
 * <pre>
 *  http패키지에서 사용하기위한 Exception
 * </pre>
 *
 * @date 2018. 02. 23.
 * @author kimhk0
 *
 */
public class HttpRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HttpRequestException() {
        super();
    }

    public HttpRequestException(Exception e) {
        super(e);
    }

    public HttpRequestException(String string) {
        super(string);
    }
}