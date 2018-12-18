package com.barry.crawl.webmagic.utils;

import com.bmw.cn.webmagic.model.ZhihuUser;
import com.bmw.cn.webmagic.utils.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取知乎用户信息
 * https://www.zhihu.com/search?q=netty学习&type=people
 *
 * */

@Slf4j
public class ZhihuPageProcessor implements PageProcessor {

    private static final String keyword = "netty学习";

    private static List list = new ArrayList();

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
            page.addTargetRequests(page.getHtml().xpath("//div[@class='List']/div/div[@class='Card SearchResult-Card']/div[@class='List-item']/div[@class='ContentItem']/div[@class='ContentItem-main']/div[@class='ContentItem-head']/h2/div/span[@class='UserLink SearchItem-userTitle']").links().all());
        } else {
            String nick = page.getHtml().xpath("//div[@class='ProfileHeader-content']/div[@class='ProfileHeader-contentHead']/h1[@class='ProfileHeader-title']/span[@class='ProfileHeader-name']/text()").get();
            String headline = page.getHtml().xpath("//div[@class='ProfileHeader-content']/div[@class='ProfileHeader-contentHead']/h1[@class='ProfileHeader-title']/span[@class='RichText ztext ProfileHeader-headline']/text()").get();
            /**
             * tree.xpath('//meta[@itemprop="keywords"]/@content')
             * */
            String homepageUrl = page.getHtml().xpath("//meta[@itemprop='url']/@content").get();
            String gender = page.getHtml().xpath("//meta[@itemprop='gender']/@content").get();
            String picUrl = page.getHtml().xpath("//meta[@itemprop='image']/@content").get();
            String agree = page.getHtml().xpath("//meta[@itemprop='zhihu:voteupCount']/@content").get();
            String thanks = page.getHtml().xpath("//meta[@itemprop='zhihu:thankedCount']/@content").get();
            String followerCount = page.getHtml().xpath("//meta[@itemprop='zhihu:followerCount']/@content").get();
            String answer = page.getHtml().xpath("//meta[@itemprop='zhihu:answerCount']/@content").get();
            String article = page.getHtml().xpath("//meta[@itemprop='zhihu:articlesCount']/@content").get();
            String ask = "";
            String collection = "";
            String location = "";
            String profession = "";
            String careerExperience = "";
            String educationalExperience = "";


            log.info("=============================>nick:"+nick+",headline:"+headline+",homepageUrl:"+homepageUrl+",gender:"+gender+",picUrl:"+picUrl+",agree:"+agree+",thanks:"+thanks+",followerCount:"+followerCount+",answer:"+answer+",article:"+article);
            ZhihuUser user = new ZhihuUser();
            user.setNick(nick);
            user.setHeadline(headline);
            user.setHomepageUrl(homepageUrl);
            user.setGender(gender);
            user.setPicUrl(picUrl);
            user.setAgree(StringUtils.isNotBlank(agree) ? Integer.parseInt(agree) : 0);
            user.setThanks(StringUtils.isNotBlank(thanks) ? Integer.parseInt(thanks) : 0);
            user.setFollowerCount(StringUtils.isNotBlank(followerCount) ? Integer.parseInt(followerCount) : 0);
            user.setAnswer(StringUtils.isNotEmpty(answer) ? Integer.parseInt(answer) : 0);
            user.setArticle(StringUtils.isNotEmpty(article) ? Integer.parseInt(article) : 0);
            //user.setAsk();
            //user.setCollection();
            list.add(user);
        }
    }

    public static void main(String[] args) {
        Spider.create(new ZhihuPageProcessor()).thread(1).addUrl("https://www.zhihu.com/search?type=people&q="+keyword).run();
        log.info("============数组存储数据111=================>"+JSONMapper.writeObjectAsString(list));
    }


}
