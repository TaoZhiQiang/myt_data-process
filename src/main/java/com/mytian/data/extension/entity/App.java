package com.mytian.data.extension.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/26
 * Time: 17:54
 * Description:
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "App")
public class App implements Serializable {

    @Id
    private String id;              //id
    private String pkg_name;       //包名
    private String name;            //APP

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
