package com.bmw.cn.webmagic.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class ZhihuUser implements Serializable {

    /** 昵称 */
    private String nick;
    /** 标题 */
    private String headline;
    /** 所在地 */
    private String location;
    /** 行业 */
    private String profession;
    /** 职业经历 */
    private String careerExperience;
    /** 教育经历 */
    private String educationalExperience;
    /** 个人简介 */
    private String recommend;
    /** 头像url */
    private String picUrl;
    /** 赞同 */
    private int agree;
    /** 感谢 */
    private int thanks;
    /** 提问数 */
    private int ask;
    /** 回答数 */
    private int answer;
    /** 文章数 */
    private int article;
    /** 收藏数 */
    private int collection;


}
