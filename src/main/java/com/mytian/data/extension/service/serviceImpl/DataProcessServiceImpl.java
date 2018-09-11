package com.mytian.data.extension.service.serviceImpl;

import com.mytian.data.commom.ActionEnum;
import com.mytian.data.extension.entity.DataProcess;
import com.mytian.data.extension.service.DataProcessService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * Created with IntelliJ IDEA.
 * Author: zhiqiang.tao
 * Description:
 * @author zhiqiang.tao
 */
@Component
public class DataProcessServiceImpl implements DataProcessService {
    private static final Logger log = LoggerFactory.getLogger( DataProcessServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    //数据采集
    @Override
    public void saveDataProcess(DataProcess dataProcess) {
        mongoTemplate.save(dataProcess);
    }

    //浏览量
    @Override
    public long findViewCount(String link, String pkg_name, String channel, String keyword,String start,String end,
                              String url,String plat) {
        Query query = getQuery( link, pkg_name, channel, start, end );
        if (StringUtils.isNotBlank(keyword)){
            query.addCriteria(Criteria.where("keyword").is(keyword));
        }

        if (StringUtils.isNotBlank(url)){
            query.addCriteria(Criteria.where("url").is(url));
        }

        if (StringUtils.isNotBlank(plat)){
            query.addCriteria(Criteria.where("plat").is(plat));
        }
        query.addCriteria(Criteria.where("action_id").is(ActionEnum.INTO.getValue()));
        long downloadCount =  mongoTemplate.count(query , com.mytian.data.extension.entity.DataProcess.class);
        return downloadCount;
    }

    //下载总数
    @Override
    public long findDownloadCount(String link, String pkg_name, String channel, String keyword,String start,String end,
                                  String url,String plat){
        Query query = getQuery( link, pkg_name, channel, start, end );
        if (StringUtils.isNotBlank(keyword)){
            query.addCriteria(Criteria.where("keyword").is(keyword));
        }

        if (StringUtils.isNotBlank(url)){
            query.addCriteria(Criteria.where("url").is(url));
        }

        if (StringUtils.isNotBlank(plat)){
            query.addCriteria(Criteria.where("plat").is(plat));
        }
        query.addCriteria(Criteria.where("action_id").is(ActionEnum.DOWNLOAD.getValue()));
        long downloadCount =  mongoTemplate.count(query , DataProcess.class);
        return downloadCount;
    }

    //没传关键字查询关键字
    @Override
    public List<String> findExcludeKeyword(String link, String pkg_name, String channel, String start, String end) {
        Query query = getQuery( link, pkg_name, channel, start, end );
//        query.limit(20);

        List<DataProcess> dataprocessList = mongoTemplate.find(query,DataProcess.class);

        List<String> keyWordLists = new ArrayList <>();

        for (com.mytian.data.extension.entity.DataProcess dataprocess:dataprocessList) {
            if (dataprocess.getKeyword() != null){
                keyWordLists.add(dataprocess.getKeyword());
            }

        }
        List keyWordList = new ArrayList(new TreeSet(keyWordLists));
        return keyWordList;

    }


    //查询所有的对象集合
    @Override
    public List <DataProcess> findDataProcessByKeyWords(String link, String pkg_name,
                                                        String channel, String start, String end,String keyword) {
        Query query = getQuery( link, pkg_name, channel, start, end );
        if (StringUtils.isNotBlank(keyword)){
            query.addCriteria(Criteria.where("keyword").is(keyword));
        }
        List<com.mytian.data.extension.entity.DataProcess> dataprocessList = mongoTemplate.find(query,DataProcess.class);
        return dataprocessList;
    }

    @Override
    public List<DataProcess> findDataProcessByKeyWord(String link, String pkg_name, String channel, String keyword,
                                                      String start, String end) {
        Query query = getQuery( link, pkg_name, channel, start, end );
        if (StringUtils.isNotBlank(keyword)){
            query.addCriteria(Criteria.where("keyword").is(keyword));
        }
        List<DataProcess> dataProcessList = mongoTemplate.find(query, com.mytian.data.extension.entity.DataProcess.class);
        return dataProcessList;
    }

    @Override
    public DataProcess findDataProcessByUrls(String link, String pkg_name, String channel, String keyword,String start,
                                             String end, String url) {
        Query query = getQuery( link, pkg_name, channel, start, end );
        if (StringUtils.isNotBlank(keyword)){
            query.addCriteria(Criteria.where("keyword").is(keyword));
        }
        if (StringUtils.isNotBlank(url)){
            query.addCriteria(Criteria.where("url").is(url));
        }
        DataProcess dataProcess = mongoTemplate.findOne(query, DataProcess.class);
        return dataProcess;
    }

    @Override
    public DataProcess getuUuid(String uuid) {
        Query query = new Query();
        if (StringUtils.isNotBlank(uuid)){
            query.addCriteria(Criteria.where("uuid").is(uuid));
        }
        DataProcess dataProcess = mongoTemplate.findOne(query, DataProcess.class);
        return dataProcess;
    }


    //通用
    private Query getQuery(String link, String pkg_name, String channel, String start, String end) {
        Query query = new Query();
        if (StringUtils.isNotBlank(link)){
            query.addCriteria(Criteria.where("link").is(link));
        }
        if (StringUtils.isNotBlank(pkg_name)){
            query.addCriteria(Criteria.where("pkg_name").is(pkg_name));
        }
        if (StringUtils.isNotBlank(channel)){
            query.addCriteria(Criteria.where("channel").is(channel));
        }

        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)){
            query.addCriteria(Criteria.where("createTime").gt(Long.parseLong(start)).lt(Long.parseLong(end)));
        }
        if (StringUtils.isNotBlank(start) && StringUtils.isBlank(end)){
            query.addCriteria(Criteria.where("createTime").gt(Long.parseLong(start)));
        }
        if (StringUtils.isBlank(start) && StringUtils.isNotBlank(end)){
            query.addCriteria(Criteria.where("createTime").lt(Long.parseLong(end)));
        }
        return query;
    }

}