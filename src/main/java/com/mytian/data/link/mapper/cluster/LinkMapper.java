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
public interface LinkMapper {

    @Select("select link from link")
    List<String> getAllLink();

    @Select("select path from link where link = #{link}")
    String getPath(String link);

    @Select("select id from link where link = #{link}")
    Integer getLinkId(String link);

}
