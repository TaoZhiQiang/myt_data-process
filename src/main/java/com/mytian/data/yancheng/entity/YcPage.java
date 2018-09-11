package com.mytian.data.yancheng.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 15:02
 * Description:盐城活动页面类
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ycPage")
public class YcPage implements Serializable {

    @Id
    private String id;
    private String pageName;
    private String pageId;

}
