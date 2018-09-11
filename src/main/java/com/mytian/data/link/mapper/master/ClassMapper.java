package com.mytian.data.link.mapper.master;

import com.mytian.data.annotation.BaseRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@BaseRepository
@Mapper
public interface ClassMapper {

    @Select("select count(t1.id) from myt_class_dic as t1 WHERE 1 = 1 and t1.pid = #{pid}")
    Integer getClassCount(Integer pid);

}
