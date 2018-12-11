package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
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

    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Long startTime, endTime;
        startTime = new Date().getTime();


        endTime = new Date().getTime();
        log.info("=====================>一共爬到"+num+"个用户信息");
    }

}
