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
@EqualsAndHashCode(callSuper = false)
@Table(name = "push_action")
public class PushAction {

    private Integer id;
    private String action;
    private Integer action_id;
}
