package com.mytian.data.link.service.impl;

import com.mytian.data.link.entity.Result;
import com.mytian.data.link.entity.User;
import com.mytian.data.link.mapper.cluster.*;
import com.mytian.data.link.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：songzelong
 * @Description：
 * @Data: create in 15:17 2018/1/10 0010
 * @Modified By：
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    LinkMapper linkMapper;

    @Resource
    ChannelMapper channelMapper;

    @Resource
    ResultMapper resultMapper;

    @Resource
    PageMapper pageMapper;

    @Resource
    PkgNameMapper pkgNameMapper;

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    KeywordMapper keyWordMapper;

    @Resource
    PushActionMapper pushActionMapper;

    //链接显示
    @Override
    public List<String> getAllLink() {
        List<String> link = linkMapper.getAllLink();
        return link;
    }

    //频道显示
    @Override
    public List<String> getAllChannel() {
        List<String> channel = channelMapper.getAllChannel();
        return channel;
    }

    //pageUrl显示
    @Override
    public List<String> getAllPage() {
        List<String> name = pageMapper.getAllPage();
        return name;
    }

    //包命显示
    @Override
    public List<String> getAllPkgName() {
        List<String> pkg = pkgNameMapper.getAllPkgName();
        return pkg;
    }

    //科别显示
    @Override
    public List<String> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    //将关键字转换成id
    @Override
    public Integer getIdByWord(String keyword) {
        return keyWordMapper.getIdByWord(keyword);
    }

    @Override
    public String getPkgName(String name) {
        String pkg_name = pkgNameMapper.getPkgName(name);
        return pkg_name;
    }

    @Override
    public void saveKeyWord(String keyword) {
        keyWordMapper.saveKeyWord(keyword);
    }

    @Override
    public String getHtml(String name) {
        return pkgNameMapper.getHtml(name);
    }

    @Override
    public String getPkg_name(String appName) {
        return pkgNameMapper.getPkgName(appName);
    }

    @Override
    public String getKeyword(Object keywordId) {
        return keyWordMapper.getKeyword(keywordId);
    }

    @Override
    public String getPage(String name) {
        return pageMapper.getPage(name);
    }

    @Override
    public String getPath(String link) {
        return linkMapper.getPath(link);
    }

    @Override
    public Integer getChannelId(String channel) {
        return channelMapper.getChannelId(channel);
    }

    @Override
    public Integer getLinkId(String link) {
        return linkMapper.getLinkId(link);
    }

    @Override
    public Integer getActionId(Integer action_id) {
        return pushActionMapper.getActionId(action_id);
    }

    @Override
    public Integer getPackgeId(String pkg_name) {
        return pkgNameMapper.getPackgeId(pkg_name);
    }

    @Override
    public String getPageId(Integer pageId) {
        return pageMapper.getPageId(pageId);
    }

    //Url转ID
    @Override
    public Integer getIdByPage(String name) {
        return pageMapper.getIdByPage(name);
    }

    //科目分类转换成id
    @Override
    public Integer getIdByName(String category) {
        return categoryMapper.getIdByName(category);
    }

    @Override
    public Integer getIdByResult(String result) {
        return resultMapper.getIdByResult(result);
    }

    @Override
    public List<String> getAllResult() {
        return resultMapper.getAllResult();
    }

    @Override
    public User findByNameAndPassword(String username) {
        return userMapper.findUserByUsernameAndPassword(username);
    }



    //保存链接
    @Override
    public void SaveResult( Result result) {
        resultMapper.saveResult(result);
    }


    public String getName(String pkg_name) {
        String name = pkgNameMapper.getName(pkg_name);
        return name;
    }

    public String getName(Integer id) {
        return pageMapper.getName(id);
    }
}
