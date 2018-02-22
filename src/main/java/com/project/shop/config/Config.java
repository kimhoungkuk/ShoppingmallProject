package com.project.shop.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.shop.http.Url;
import com.project.shop.utils.Closer;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;

/**
 *
 * 클래스명: <code>Config</code>
 *
 * <pre>
 * 설정 파일 load관리
 * abcmart.xml의 환경변수를 메모리에 간직하는 클래스
 * </pre>
 *
 * @date 2018. 02. 22.
 * @author kimhk0
 *
 */
public class Config implements ConfigKey {

    private static Properties properties = new Properties();

    public Config(Resource location) {
        try {
            if (location.exists()) {
                properties.loadFromXML(location.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfiguration(String path) {
        try {
            File file = new File(path);
            InputStream input = new FileInputStream(file);
            properties.loadFromXML(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String key) {
        String propertiesValue = properties.getProperty(key);
        if (propertiesValue == null) {
            throw new ConfigValueNotFoundException(key + " 에 해당하는 환경 변수값을 찾을 수 없음");
        } else {
            return propertiesValue;
        }
    }

    public static String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new ConfigValueNotFoundException(key + " 에 해당하는 환경 변수값을 찾을 수 없음");
        } else {
            try {
                return Integer.parseInt(properties.getProperty(key));
            } catch (NumberFormatException e) {
                throw new ConfigValueNotFoundException(key + " 에 값[" + value + "]는 정수여야함");
            }
        }
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key) {
        return stringToBoolean(properties.getProperty(key));
    }

    private static boolean stringToBoolean(String value) {
        if (value == null) {
            return false;
        }
        if (value.equals("true")) {
            return true;
        }
        if (value.equals("on")) {
            return true;
        }
        if (value.equals("yes")) {
            return true;
        }
        if (value.equals("1")) {
            return true;
        }
        if (value.equals("Y")) {
            return true;
        }
        return false;
    }
    
    public static long getLong(String key, long defaultValue) {
        try {
            return Long.parseLong(properties.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Properties properties() {
        return (Properties) properties.clone();
    }

    /**
     * newline 으로 분리되는 설정값 리스트를 반환한다.
     *
     * @param key
     * @return \n으로 분리되는 설정값 리스트
     */
    public static List<String> getStrings(String key) {
        String propertyValue = getString(key);
        BufferedReader reader = null;
        StringReader stringReader = new StringReader(propertyValue);

        List<String> propertyValues = new ArrayList<String>();
        try {
            reader = new BufferedReader(stringReader);
            for (;;) {
                String line = readline(reader);
                if (line == null) {
                    break;
                } else if (StringUtils.isEmpty(line.trim())) {
                    continue;
                } else {
                    propertyValues.add(line.trim());
                }
            }
        } finally {
            if (reader != null) {
                Closer.close(reader);
            }
        }

        return propertyValues;
    }

    /**
     * Exception 안나
     * @param reader
     * @return
     */
    private static String readline(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getServerCharset() {
        return getString(KEY_SERVER_CHARSET);
    }

    /**
     * 이미지서버 호스트(SSL) 
     */
    public static String getImageServerHostForSSL() {
        return getImageServerHost(true);
    }

    /**
     * 이미지서버 호스트 
     * ssl: true -> https, false -> http 
     * 
     */
    private static String getImageServerHost(boolean ssl) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ssl ? "https" : Url.DEFAULT_PROTOCOL);
        buffer.append("://");
        buffer.append(Config.getString("image.server.host"));
        int port = Config.getInt("image.server.port", 80);
        if (port != 80) {
            buffer.append(":");
            buffer.append(port);
        }
        
        return buffer.toString();
    }
    
    /**
     * 이미지서버 호스트 
     */
    public static String getImageServerHost() {
        return getImageServerHost(false);
    }
    
    /**
     * 업로드 이미지 Host/Url
     *
     * @return
     */
    public static String getUploadImageUrl() {
        StringBuilder buffer = new StringBuilder(Config.getImageServerHost());
        buffer.append(Config.getString("image.upload.uri"));
        return buffer.toString();
    }
    
    /**
     * 업로드 이미지 Host/Url 상품상세전용
     * @return
     */
    public static String getProductUploadImageUrl() {
        StringBuilder buffer = new StringBuilder(Config.getImageServerHost());
        buffer.append(Config.getString("image.server.product.upload.path"));
        return buffer.toString();
    }
    
    /**
     * 멤버쉽 서버
     * ex) http://192.168.1.219:88 
     * @return
     */
    public static String getMemberShipServer() {
        StringBuilder buffer = new StringBuilder(512);
        buffer.append(Url.DEFAULT_PROTOCOL);
        buffer.append("://");
        buffer.append(Config.getString("membership.ip"));
        int port = Config.getInt("membership.port", 80);
        if (port != 80) {
            buffer.append(":");
            buffer.append(port);
        }
       return buffer.toString();
    }

    /**
     * 후론트 서버
     * @return
     */
    public static String getServerHost() {
        return getServerHost(false); 
    }

    /**
     * 전시 미리보기, 업데이트, 즉시반영을위한
     * dev-front.abcmart.co.kr 세팅용
     * 위영역에서  ${serverHostFront}를 사용하면된다.
     * 백오피스 전체 ssl작업하면서 기존 후론트 속이기가 안멱혀서.. 
     * @return
     */
    public static String getServerHostDev(boolean ssl) {
        
        String host;
        try {
            host = Config.getString("server.host.admin");
        } catch (Exception e) {
            host = Config.getString("server.host");
        }
        
        StringBuilder buffer = new StringBuilder(512);
        buffer.append(ssl ? "https" : Url.DEFAULT_PROTOCOL);
        buffer.append("://");
        buffer.append(host);
        int port = Config.getInt("server.port", 80);
        if (port != 80) {
            buffer.append(":");
            buffer.append(port);
        }
        return  buffer.toString();
    }

    /**
     * 후론트 서버 (SSL)
     * @return
     */
    public static String getServerHostForSSL() {
        return getServerHost(true); 
    }
    
    private static String getServerHost(boolean ssl) {
        StringBuilder buffer = new StringBuilder(512);
        if(!Config.getString("server.host").contains("www.abcmart.co.kr") && !Config.getString("server.host").contains("as.abcmart.co.kr") && !Config.getString("server.host").contains("dev-front.abcmart.co.kr"))
        {
            ssl = false;
        }
        buffer.append(ssl ? "https" : Url.DEFAULT_PROTOCOL);
        buffer.append("://");
        buffer.append(Config.getString("server.host"));
        int port = Config.getInt("server.port", 80);
        if (port != 80) {
            buffer.append(":");
            buffer.append(port);
        }
        
        return buffer.toString();
    }
    
    /**
     * SSL 요청 host정보를 반환함.
     *     현재 포트는 지정하지 않았음.
     * @return
     */
    public static String getServerHostSSL(){
        String host;
        try {
            host = Config.getString("server.host.admin");
        } catch (Exception e) {
            host = Config.getString("server.host");
        }
        
        StringBuilder buffer = new StringBuilder(512);
        buffer.append("https://");
        buffer.append(host);
        return buffer.toString();
    }
    
    
    /**
     * 백오피스 서버
     * @return
     */
    public static String getBackOfficeHost() {
        String service = getString("lgup.cst.platform", "test");
        String key = "test".equals(service) ? "backoffice.test.server" : "backoffice.server";
        String host = getString(key);
        int port = 8080;
        StringBuilder buffer = new StringBuilder(512);
        buffer.append(Url.DEFAULT_PROTOCOL);
        buffer.append("://");
        buffer.append(host);
        if (port != 80) {
            buffer.append(":");
            buffer.append(port);
        }
        
        return buffer.toString();
    }
}