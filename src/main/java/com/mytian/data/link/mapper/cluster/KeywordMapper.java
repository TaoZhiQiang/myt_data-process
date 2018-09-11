package com.mytian.data.link.mapper.cluster;

import com.mytian.data.annotation.BaseRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
@BaseRepository
public interface KeywordMapper {

    @Select("select id from keyword WHERE keyword = #{keyword}")
    Integer getIdByWord(String keyword);

    @Insert("insert into keyword(keyword)" + "values(#{keyword})")
    void saveKeyWord(String keyword);

    @Select("select keyword from keyword WHERE id = #{keywordId}")
    String getKeyword(Object keywordId);
}
