package com.mytian.data.link.service;


import com.mytian.data.link.entity.Result;
import com.mytian.data.link.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: ${DATE}
 * Time: ${TIME}
 * Description:
 */
public interface ProductService {


    List<String> getAllChannel();

    List<String> getAllLink();

    void SaveResult(Result result);

    List<String> getAllPage();

    List<String> getAllPkgName();

    Integer getIdByPage(String name);

    Integer getIdByName(String keyWord);

    Integer getIdByResult(String result);

    List<String> getAllResult();

    User findByNameAndPassword(String username);

    List<String> getAllCategory();

    Integer getIdByWord(String keyword);

    String getPkgName(String name);

    void saveKeyWord(String keyWord);

    String getHtml(String name);

    String getPkg_name(String appName);

    String getKeyword(Object keywordId);

    String getPage(String name);

    String getPath(String link);

    Integer getChannelId(String channel);

    Integer getLinkId(String link);

    Integer getActionId(Integer action_id);

    Integer getPackgeId(String pkg_name);

    String getPageId(Integer pageId);
}
