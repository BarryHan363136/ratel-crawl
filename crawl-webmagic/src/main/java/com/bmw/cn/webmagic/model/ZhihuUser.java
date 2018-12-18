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
    /** 性别 */
    private String gender;
    /** 个人简介 */
    private String recommend;
    /** 个人知乎url */
    private String homepageUrl;
    /** 头像url */
    private String picUrl;
    /** 赞同 */
    private Integer agree;
    /** 感谢 */
    private Integer thanks;
    /** 跟帖数 */
    private Integer followerCount;
    /** 提问数 */
    private Integer ask;
    /** 回答数 */
    private Integer answer;
    /** 文章数 */
    private Integer article;
    /** 收藏数 */
    private Integer collection;


}
