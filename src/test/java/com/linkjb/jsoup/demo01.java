package com.linkjb.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author sharkshen
 * @data 2019/1/19 20:37
 * @Useage
 */
public class demo01 {
    private static Logger log = LoggerFactory.getLogger(demo01.class);
    /**
     * 将字符串转化为Document
     *
     * html: https://jsoup.org/cookbook/input/parse-document-from-string
     */
    @Test
    public void parseDocumentFromString() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        log.info("parseDocumentFromString content={}", doc);
    }
    //从网页生成Document
    /**
     * 从网络上加载网页并转化为Document
     *
     * html: https://jsoup.org/cookbook/input/load-document-from-url
     */
    @Test
    public void loadDocumentFromURL() {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.baidu.com/").get();
            // 从document中获取title值
            String title = doc.title();
            log.info("LoadDocumentFromURL title={}", title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 从本地加载文件并转化为Document
     *
     * html: https://jsoup.org/cookbook/input/load-document-from-file
     */
    public void loadDocumentFromFile() {
        URL fileUrl =demo01.class.getResource("https://www.baidu.com/");
        File input = new File(fileUrl.getFile());
        try {
            /* The baseUri parameter is used by the parser to resolve relative URLs in the document
             * before a <base href> element is found. If that's not a concern for you,
             * you can pass an empty string instead.
             */
            Document doc = Jsoup.parse(input, "UTF-8", "https://www.baidu.com/");
            log.info("LoadDocumentFromFile content={}", doc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *  调用document的类似DOM的方法获取Element
     *
     * html: https://jsoup.org/cookbook/extracting-data/dom-navigation
     * @throws IOException
     */
    @Test
    public void extractDataByDOM() throws IOException{
        Document doc = Jsoup.connect("https://www.baidu.com/").get();
        Element lg = doc.getElementById("lg");
        log.info("getElementById lg = {}", lg);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            log.info("linkHref={}, linkText={}",linkHref, linkText);
        }
    }

}
