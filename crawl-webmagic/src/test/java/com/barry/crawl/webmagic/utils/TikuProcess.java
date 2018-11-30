package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * https://blog.csdn.net/chen45682kang/article/details/75810033
 * */

/** 1.实现PageProcessor接口 */
@Slf4j
public class TikuProcess implements PageProcessor {

    /** 2.设置抓取网站的相关配置 */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    /** 3.返回site */
    @Override
    public Site getSite() {
        return this.site;
    }

    /** 5.爬去逻辑 */
    @Override
    public void process(Page page) {
        page.putField("html", page.getHtml());
        String pageHtml = page.getResultItems().get("html").toString();
        //log.info("===============>pageHtml:"+pageHtml);
    }

    /**
     * 4. 爬取
     * */
    public static void main(String[] args) {
        String url = "http://www.tiku.com/testPaper.html?sct=0&cn=%E6%95%B0%E5%AD%A6&st=2&cid=500004&bid=800009&vid=800005&uid=800003&cptid=700005&kid=700124&qtid=600050&difficulty=&sort=0";
        /** 添加爬取的链接 */
        Spider spider = Spider.create(new TikuProcess()).
                addUrl(url).thread(5);
        spider.run();
    }


}
