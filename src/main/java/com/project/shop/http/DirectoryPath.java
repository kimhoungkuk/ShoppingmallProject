package com.project.shop.http;

import java.util.ArrayList;
import java.util.List;

/**
 * 클래스명: <code>DirectoryPath</code>
 * 
 * <pre>
 * '/'와 가운데 디랙토리를 분석하기위한 유틸
 * 역시 클래스명 짓기는 힘들다.
 * </pre>
 *
 * @date 2018. 02. 23.
 * @author kimhk0
 *
 */
public class DirectoryPath {

    private int index = 0;

    private final char seperator = '/';

    /**
     * 기본유알엘
     */
    private String basePath = null;

    /**
     * 디랙토리리스트
     */
    private List<String> dirList = new ArrayList<String>();

    /**
     *
     */
    private final StringBuilder other = new StringBuilder();

    /**
     * 파일명
     */
    private String fileName = null;

    public DirectoryPath(String path) {
        this.basePath = path;
    }

    public DirectoryPath() {

    }

    /**
     * <pre>
     * 디랙토리 리스트와
     * 파일로 저장한다.
     * </pre>
     *
     * @throws HttpRequestException
     */
    public void analysis() {

        /*
         *  2008-03-14
         *  basePath 링크가 baseurl?directory=/abc/efg/
         *  이런형식일때 생기는 버그
         *  basePath의 쿼리를 때어낸다.
         *  후에 파일명에 다시 쿼리를 붙인다.
         */
        boolean isBreakPoint = false;
        String tempBasePath = "";
        for (int i = 0; i < basePath.length(); i++) {
            char ch = basePath.charAt(i);
            if (ch == '?' || ch == ';' || ch == '#') {
                isBreakPoint = true;
            }

            if (isBreakPoint) {
                other.append(ch);
            } else {
                tempBasePath += ch;
            }
        }

        // 분석전에 파일명은 초기화해둔다.
        fileName = "";
        String newDir = "";
        Character preChar = null;
        for (int i = 0; i < tempBasePath.length(); i++) {
            char ch = tempBasePath.charAt(i);
            if (ch == seperator && i == 0) {
                this.dirList = new ArrayList<String>();
            } if (ch == seperator) {
                if (preChar != null && preChar == ':') {
                    newDir += ch;
                } else if ("".equals(newDir) == false) {
                    if ("..".equals(newDir)) {
                        if (this.dirList.isEmpty()) {
                            /*
                             *  throw new ScrapperException("First Directory is [..]");
                             *  수정 2008-03-10
                             *  브라우져 동작과 같이간다.
                             *  첫디렉토리에 [..]인데 상위디랙토리가없으면 [..]를 무시한다.
                             */
                        } else {
                            this.dirList.remove(this.dirList.size() - 1);
                        }
                        newDir = "";
                        continue;
                    }

                    if (".".equals(newDir)) {
                        newDir = "";
                    } else {
                        this.dirList.add(newDir);
                        newDir = "";
                    }
                }
            } else {
                newDir += ch;
            }

            preChar = ch;
        }

        this.fileName = newDir;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getDirectories() {
        return this.dirList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <pre>
     * 클래스정보를 문자열로 리턴
     * </pre>
     *
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(4096);
        if (this.dirList != null) {
            buffer.append("[DIRS]=");
            for (int i = 0; i < dirList.size(); i++) {
                buffer.append("[" + dirList.get(i) + "]");
            }
        }
        if (this.fileName != null) {
            buffer.append("\n").append("[fileName]=" + this.fileName);
        }

        return buffer.toString();
    }

    /**
     *
     * @return
     */
    public String getURI() {
        return this.getPath() + this.getFileName();
    }

    /**
     *
     * @return
     */
    public String getPath() {
        StringBuffer buffer = new StringBuffer(4096);
        buffer.append(seperator);
        for (String dir: dirList) {
            buffer.append(dir).append(seperator);
        }

        return buffer.toString();
    }

    public String getFullPath() {
        return this.getURI() + this.other;
    }
}