package com.barry.crawl.webmagic.utils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyCnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        if (!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()) {
            page.addTargetRequests(
                    page.getHtml().xpath("//*[@id=\"mainContent\"]/div/div/div[@class=\"postTitle\"]/a/@href").all());
        } else {
            page.putField(page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/text()").toString(),
                    page.getHtml().xpath("//*[@id=\"cb_post_title_url\"]/@href").toString());
        }
    }

    public static void main(String[] args) {
        Spider.create(new MyCnblogsSpider()).addUrl("http://www.cnblogs.com/justcooooode/")
                .addPipeline(new JsonFilePipeline("C:\\workspaces\\tmp_files\\webmagic")).run();
    }

}
