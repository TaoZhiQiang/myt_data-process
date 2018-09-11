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
public interface PkgNameMapper {

    @Select("select name from pkg_name")
    List<String> getAllPkgName();

    @Select("select pkg_name from pkg_name where name = #{name}")
    String getPkgName(String name);

    @Select("select name from pkg_name where pkg_name = #{pkg_name}")
    String getName(String pkg_name);

    @Select("select html from pkg_name where name = #{name}")
    String getHtml(String name);

    @Select("select id from pkg_name where pkg_name = #{pkg_name}")
    Integer getPackgeId(String pkg_name);
}
