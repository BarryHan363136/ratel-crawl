package com.barry.crawl.webmagic.utils;

import com.bmw.cn.webmagic.utils.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 爬取知乎用户信息
 * https://www.zhihu.com/search?q=netty学习&type=people
 *
 * */

@Slf4j
public class ZhihuPageProcessor implements PageProcessor {

    private static final String keyword = "netty学习";

    private static List list = new ArrayList<>();

    private Site site = Site.me().
            setRetryTimes(3).setSleepTime(1000).
            setTimeOut(50000).setCharset("UTF-8").
            setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");

    @Override
    public Site getSite() {
        return this.site;
    }

    /**
     * 匹配任意字符 [\s\S]* 或者 [\w\W]*
     * */
    @Override
    public void process(Page page) {
        log.info("================>page.getUrl:"+page.getUrl());
        if(page.getUrl().regex("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+").match()){
            page.addTargetRequests(page.getHtml().xpath("//div[@class='List']/div/div[@class='List-item']/div[@class='ContentItem']/div[@class='ContentItem-main']/div[@class='ContentItem-image']").links().all());
        } else {
            String name = page.getHtml().xpath("//span[@class='ProfileHeader-name']/text()").get();
            String name2 = page.getHtml().xpath("//div[@class='ProfileHeader-content']/div[@class='ProfileHeader-contentHead']/h1[@class='ProfileHeader-title']/span[@class='ProfileHeader-name']/text()").get();
            log.info("=============================>name:"+name+",name2:"+name2);
            list.add(name);
        }
    }

    public static void main(String[] args) {
        Spider.create(new ZhihuPageProcessor()).thread(1).addUrl("https://www.zhihu.com/search?type=people&q="+keyword).run();
        log.info("============数组存储数据111=================>"+JSONMapper.writeObjectAsString(list));
    }


}
