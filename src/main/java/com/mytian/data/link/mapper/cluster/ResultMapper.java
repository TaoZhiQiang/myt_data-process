package com.mytian.data.link.mapper.cluster;

import com.mytian.data.annotation.BaseRepository;
import com.mytian.data.link.entity.Result;
import org.apache.ibatis.annotations.Insert;
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
public interface ResultMapper {

    @Insert("insert into result(id,result)" + "values(#{id},#{result})")
    void saveResult( Result result);

    @Select("select id from result where result = #{result}")
    Integer getIdByResult(String result);

    @Select("select result from result order by id desc  limit 0,20")
    List<String> getAllResult();
}
