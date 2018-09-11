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
public interface PageMapper {

    @Select("select name from page")
    List<String> getAllPage();

    @Select("select id from page where name = #{name}")
    Integer getIdByPage(String name);

    @Select("select name from page where id = #{id}")
    String getName(Integer id);

    @Select("select page from page where name = #{name}")
    String getPage(String name);

    @Select("select page from page where id = #{pageId}")
    String getPageId(Integer pageId);
}
