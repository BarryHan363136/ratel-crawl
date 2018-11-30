package com.barry.crawl.webmagic.utils;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/u014352080/article/details/79530485
 * */
@Slf4j
public class HQProcessor implements PageProcessor {

    public static final String index_list = "(.*).huanqiu.com/(.*)pindao=(.*)";//校验地址正则
    //public static String pic_dir = fun.getProValue(PINDAO_PIC_FILE_PATH);//获取图片保存路径

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(6000);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        log.info("=============>page:"+page.getUrl());
        //page.putField("html", page.getHtml());
        //String pageHtml = page.getResultItems().get("html").toString();
        //log.info("===============>pageHtml:"+pageHtml);
    }

    public static void main(String[] args) {
        /** 设置频道 */
        List<String> channelIds = new ArrayList<String>();
        channelIds.add("world");
//        channelIds.add("mil");
//        channelIds.add("china");
//        channelIds.add("taiwan");
//        channelIds.add("opinion");
//        channelIds.add("society");
//        channelIds.add("oversea");

        String prefix = "http://";
        String suffix = ".huanqiu.com/";
        for (String channelId : channelIds){
            String url = prefix + channelId + suffix;
            log.info("===================>url:"+url);
            Spider.create(new HQProcessor()).addUrl(url).thread(3).run();
        }
    }


}
