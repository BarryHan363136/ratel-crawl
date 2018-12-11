package com.bmw.cn.webmagic.processor;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ZhiHuProcessor implements PageProcessor {

    private List<Pipeline> pipelines = new ArrayList<>();
    private Site site;

    public ZhiHuProcessor(Pipeline... pipelines) {
        this.site = Site.me().setRetryTimes(3).
                setRetrySleepTime(10).
                setSleepTime(1000).
                setTimeOut(10000).
                setCharset("UTF-8").
                setDomain("https://www.zhihu.com").
                addCookie("", "");
        Collections.addAll(this.pipelines, pipelines);
    }

    @Override
    public void process(Page page) {
                                  //xpath("//div[@class='detail-wrapper']//div[@class='header ']/a/div/span[@class='name']/text()")
                                  //xpath("//div[@class='detail-wrapper']//div[@class='header ']/a/div/span[@class='time timeago']/text()")
        //Object obj = page.getHtml().xpath("//div[@class='Tag QuestionTopic']//h1[@class='QuestionHeader-title']").all();
        String question = page.getHtml().xpath("//h1[@class='QuestionHeader-title']/text()").get();








    }

    @Override
    public Site getSite() {
        return this.site;
    }

}
