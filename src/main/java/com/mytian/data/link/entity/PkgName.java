package com.mytian.data.link.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/18
 * Time: 9:45
 * Description:
 */

@Data
@EqualsAndHashCode(callSuper = false)//这两个注解配合使用，代替get，set等一系列方法，开启true时避免equals方法的hash问题。
@Table(name = "pkg_name")
public class PkgName {

    private Integer id;
    private String pkg_name;
    private String name;
    private String html;
}
