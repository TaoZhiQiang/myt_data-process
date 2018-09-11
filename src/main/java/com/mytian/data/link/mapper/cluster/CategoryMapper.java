package com.mytian.data.link.mapper.cluster;

import com.mytian.data.annotation.BaseRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Description:
 */
@Mapper
@BaseRepository
public interface CategoryMapper {

    @Select("select id from category WHERE name = #{category}")
    Integer getIdByName(String category);

    @Select("select name from category")
    List<String> getAllCategory();
}
