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
public interface ChannelMapper {

    @Select("select channel_en from channel")
    List<String> getAllChannel();

    @Select("select id from channel where channel_en = #{channel}")
    Integer getChannelId(String channel);

}
