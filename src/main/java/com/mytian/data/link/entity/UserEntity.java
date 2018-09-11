package com.mytian.data.link.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/19
 * Time: 17:11
 * Description:
 */

@Data
@EqualsAndHashCode(callSuper = false)//这两个注解配合使用，代替get，set等一系列方法，开启true时避免equals方法的hash问题。
@Table(name = "UserEntity")
public class UserEntity  implements Serializable {
    @Id
    private Integer id;
    private String userName;
    private String passWord;
}
