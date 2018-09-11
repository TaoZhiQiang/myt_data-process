package com.mytian.data.link.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode()
@Table(name = "myt_market_user_action_log")
public class MytMarketUserAction {
    private Integer id;
    private Integer uid;
    private String action_id;
    private Integer device_id;
    private String channel;
    private String pkg_name;
    private String device_type;
    private String version;
    private String time;
}
