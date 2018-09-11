package com.mytian.data.link.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/16
 * Time: 19:50
 * Description:
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "user")
public class User {
    private Integer id;
    private String username;
    private String password;
}
