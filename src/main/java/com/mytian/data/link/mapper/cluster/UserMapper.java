package com.mytian.data.link.mapper.cluster;

import com.mytian.data.annotation.BaseRepository;
import com.mytian.data.link.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Description:
 */
@Mapper
@BaseRepository
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findUserByUsernameAndPassword(String username);
}
