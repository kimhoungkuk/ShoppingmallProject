package com.project.shop.utils;

import java.io.Closeable;

/**
 * 클래스명: <code>IOCloser</code>
 * <pre>
 * Clonable클래스들의 안전 close를 처리하는 Utils
 * </pre>
 *
 * @date 2018. 02. 23.
 * @author kimhk0
 *
 */
public class Closer {

    /**
     * <pre>
     *  Closeable(o) 에 해당하는 Close
     * </pre>
     * @param o
     */
    public static void close(Object o) {
        if (o == null) {
            return;
        }

        if (o instanceof Closeable) {
            Closeable c = (Closeable) o;
            try {
                c.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    /**
     * <pre>
     *  복수의 Closeable 에 해당하는 Close
     * </pre>
     * @param parameterTypes
     */
    public static void close(Object... parameterTypes) {
        for (Object o: parameterTypes) {
            close(o);
        }
    }
    
/*
    public static void close(Object[] os) {
        for (Object o: os) {
            close(o);
        }
    }
*/  
}