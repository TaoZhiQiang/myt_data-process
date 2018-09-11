package com.mytian.data.extension.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/19
 * Time: 17:54
 * Description:
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "dataProcess")
public class DataProcess implements Serializable {

    @Id
    private String id;              //id
    private String uuid;            //uuid
    private String ip;              //请求ip
    private String url;             //请求的链接
    private String link;            //域名
    private String pkg_name;        //APP包名
    private String channel;         //渠道
    private String plat;            //平台
    private Integer pageId;             //页面ID
    private String keyword;         //关键词，推广关键词。
    private Long keywordId;        //关键词ID，推广关键词ID。
    @Indexed
    private Integer action_id;      //行为id，（1.浏览，2.下载）
    private Long createTime;        //时间戳



}
