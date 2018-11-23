package com.barry.crawl.jsoup.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Jsoup API处理HTML文档的例子
 *
 */

@Slf4j
public class JsoupFunctionTest {

    /**
     * 1. 载入文件从URL加载文档，使用Jsoup.connect()方法从URL加载HTML。
     */
    @Test
    public void testJsoupConnect(){
        try {
            Document document = Jsoup.connect("http://www.yibai.com").get();
            log.info("=====>"+document.title());
        } catch (Exception e) {
            log.error("testJsoupConnect error={} ", e);
        }
    }

    /**
     * 2. 从文件加载文档使用
     *    Jsoup.parse()方法从文件加载HTML。
     * */
    @Test
    public void testJsoupParseHtmlFile(){
        try {
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\index.html"), "UTF-8");
            log.info("=====>"+document.title());
        } catch (IOException e) {
            log.error("testJsoupHtmlFile error={} ", e);
        }
    }

    /**
     * 3. 从String加载文档
     * 使用Jsoup.parse()方法从字符串加载HTML
     * */
    @Test
    public void testJsoupParseStringHtml(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document document = Jsoup.parse(html);
        log.info("=====>"+document.title());
    }

    /**
     * 4. 从HTML获取标题
     * 如上图所示，调用document.title()方法获取HTML页面的标题。
     * */
    @Test
    public void testHtmlFileParse(){
        Document document = null;
        try {
            document = Jsoup.parse( new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\index.html"), "utf-8");
            log.info(document.title());
        } catch (IOException e) {
            log.error("testHtmlFileParse error={} ", e);
        }
    }

    /**
     * 5. 获取HTML页面的Fav图标
     * 假设favicon图像将是HTML文档的<head>部分中的第一个图像，您可以使用下面的代码
     * */
    @Test
    public void testGetHtmlFav() {
        try {
            String favImage = "Not Found";
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\yiibai-index.html"), "utf-8");
            Element element = document.head().select("link[href~=.*\\.(ico|png)]").first();
            if (element == null) {
                element = document.head().select("meta[itemprop=image]").first();
                if (element != null) {
                    favImage = element.attr("content");
                }
            } else {
                favImage = element.attr("href");
            }
            log.info("==============>"+favImage);
        } catch (IOException e) {
            log.error("testGetHtmlFav error={} ", e);
        }
    }

    /**
     * 6. 获取HTML页面中的所有链接
     *    要获取网页中的所有链接
     */
    @Test
    public void testGetHtmlAllLinks() {
        try {
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\yiibai-index.html"), "utf-8");
            Elements links = document.select("a[href]");
            for (Element link : links) {
                log.info("==============>link : "+link.attr("href")+" ,text : "+link.text());
            }
        } catch (IOException e) {
            log.error("testGetHtmlAllLinks error={} ", e);
        }
    }

    /**
     * 7. 获取HTML页面中的所有图像
     *    要获取网页中显示的所有图像
     * */
    @Test
    public void testGetHtmlAllImages() {
        try {
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\yiibai-index.html"), "utf-8");
            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {
                log.info("==============>src : " + image.attr("src"));
                log.info("==============>height : " + image.attr("height"));
                log.info("==============>width : " + image.attr("width"));
                log.info("==============>alt : " + image.attr("alt"));
            }
        } catch (Exception e) {
            log.error("testGetHtmlAllImages error={} ", e);
        }
    }

    /**
     * 8. 获取URL的元信息
     *    元信息包括Google等搜索引擎用来确定网页内容的索引为目的。 它们以HTML页面的HEAD部分中的一些标签的形式存在。 要获取有关网页的元信息
     * */
    @Test
    public void testGetUrlMeta(){
        try {
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\yiibai-index.html"), "utf-8");
            //Document document = Jsoup.connect("https://www.yiibai.com").get();
            String description = document.select("meta[name=description]").get(0).attr("content");
            log.info("==============>Meta description : " + description);

            String keywords = document.select("meta[name=keywords]").first().attr("content");
            log.info("==============>Meta keyword : " + keywords);
        } catch (Exception e) {
            log.error("testGetUrlMeta error={} ", e);
        }
    }

    /**
     * 9. 在HTML页面中获取表单属性
     * 在网页中获取表单输入元素非常简单。 使用唯一ID查找FORM元素; 然后找到该表单中存在的所有INPUT元素。
     * */
    @Test
    public void testGetHtmlForm(){
        try {
            Document document = Jsoup.parse(new File("C:\\Users\\qxv0963\\Desktop\\testfiles\\yiibai-index.html"), "utf-8");
            Element formElement = document.getElementById("search-form");

            Elements inputElements = formElement.getElementsByTag("input");
            for (Element inputElement : inputElements) {
                String key = inputElement.attr("name");
                String value = inputElement.attr("value");
                log.info("==============>Param name: "+key+" \nParam value: "+value);
            }
        } catch (Exception e) {
            log.error("testGetHtmlForm error={} ", e);
        }
    }











}
