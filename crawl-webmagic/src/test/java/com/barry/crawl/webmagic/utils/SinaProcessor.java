package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Slf4j
public class SinaProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000).setTimeOut(10000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        log.info("=============>"+page.getUrl());

    }

    public static void main(String[] args) {
        String url = "https://d.weibo.com/";

        Spider.create(new SinaProcessor()).addUrl(url).addPipeline(new ConsolePipeline()).run();
    }

}
