package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 爬取知乎用户信息
 * https://blog.csdn.net/antgan/article/details/51304255
 * https://www.zhihu.com/search?type=people&q=java%20springboot
 *
 * */

@Slf4j
public class ZhihuPageProcessor implements PageProcessor {

    private static final AtomicInteger integer = new AtomicInteger();

    private static final String keyword = "java springboot";

    private Site site = Site.me().
            setRetryTimes(3).setSleepTime(1000).
            setTimeOut(50000).setCharset("UTF-8").
            setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");

    @Override
    public Site getSite() {
        return this.site;
    }

    @Override
    public void process(Page page) {
        integer.incrementAndGet();
        String name = page.getHtml().xpath("//div[@class='null-toggle']/span[@class='Highlight']/text()").get();
        String name2 = page.getHtml().xpath("//div[@class='Popover']/div[@class='null-toggle']/a[@class='UserLink-link']/span[@class='Highlight']/text()").get();
        log.info("=============================>name:"+name+",name2:"+name2);
    }


    public static void main(String[] args) {
        Spider.create(new ZhihuPageProcessor()).thread(1).addUrl("https://www.zhihu.com/search?q=springboot&type="+keyword).run();
    }


}
