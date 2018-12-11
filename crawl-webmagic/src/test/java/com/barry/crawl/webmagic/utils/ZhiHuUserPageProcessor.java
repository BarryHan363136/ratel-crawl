package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.ZhihuPageProcessor;

import java.util.Date;

/**
 * 爬取知乎用户信息
 * https://blog.csdn.net/antgan/article/details/51304255
 * */

@Slf4j
public class ZhiHuUserPageProcessor implements PageProcessor {

    /** 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等 */
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setTimeOut(10000).setSleepTime(1000);
    /** 用户数量 */
    private static Integer num = 0;
    /** 搜索关键词 */
    private static String keyword = "JAVA";

    @Override
    public void process(Page page) {
        /** 1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中 */
        if (page.getUrl().regex("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+").match()){

        } else {
            /** 2. 如果是用户详细页面 */

        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Long startTime, endTime;
        startTime = new Date().getTime();
        Spider.create(new ZhihuPageProcessor()).
                addUrl("https://www.zhihu.com/search?type=people&q="+keyword).
                thread(5).
                run();

        endTime = new Date().getTime();
        log.info("=====================>一共爬到:"+num+"个用户信息,用时:"+(endTime-startTime)/1000+"秒");
    }

}
