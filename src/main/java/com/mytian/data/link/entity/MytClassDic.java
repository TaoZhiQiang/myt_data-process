package com.mytian.data.link.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)//这两个注解配合使用，代替get，set等一系列方法，开启true时避免equals方法的hash问题。
@Table(name = "myt_class_dic")
public class MytClassDic {

    private Integer id;//课程id

    private Integer pid;//父级课程id

    private Integer type;//分类

    private String key_;

    private String value;

    private String obj1;

    private String obj2;

    private String obj3;

    private String obj4;

    private String obj5;

    private String obj6;

    private String obj7;

    public MytClassDic newInstance(){
        try {
            return (MytClassDic) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
