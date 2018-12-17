package com.bmw.cn.webmagic.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class ZhihuUser implements Serializable {

    /** 昵称 */
    private String nick;
    /** 标题 */
    private String headline;
    /** 身份 */
    private String identity;
    /** 所在地 */
    private String location;
    /** 行业 */
    private String profession;
    /** 性别 */
    private int sex;
    /** 学校 */
    private String school;
    /** 专业 */
    private String major;
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
