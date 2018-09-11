package com.mytian.data.yancheng.service.serviceImpl;

import com.mytian.data.extension.service.serviceImpl.DataProcessServiceImpl;
import com.mytian.data.yancheng.entity.YanCheng;
import com.mytian.data.yancheng.entity.YcAction;
import com.mytian.data.yancheng.entity.YcPage;
import com.mytian.data.yancheng.service.YanChengService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 15:07
 * Description:
 */
@Component
public class YanChengServiceImpl implements YanChengService {
    private static final Logger log = LoggerFactory.getLogger( DataProcessServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    //添加所有页面
    void saveYCPage(YcPage ycPage) {
        mongoTemplate.save(ycPage);
    }

    //添加所有用户行为
    void saveYCAction(YcAction ycAction) {
        mongoTemplate.save(ycAction);
    }

    @Override
    public void saveYanCheng(YanCheng yanCheng) {
        mongoTemplate.save(yanCheng);
    }

    @Override
    public YcPage getPageName(String pageId) {
        Query query = new Query();
        if (StringUtils.isNotBlank(pageId)){
            query.addCriteria(Criteria.where("pageId").is(pageId));
        }
        YcPage ycPage = mongoTemplate.findOne(query,YcPage.class);
        return ycPage;
    }

    @Override
    public YcPage getPageId(String pageName) {
        Query query = new Query();
        if (StringUtils.isNotBlank(pageName)){
            query.addCriteria(Criteria.where("pageName").is(pageName));
        }
        YcPage ycPage = mongoTemplate.findOne(query,YcPage.class);
        return ycPage;
    }

    @Override
    public YcAction getAction(String action_id) {
        Query query = new Query();
        if (StringUtils.isNotBlank(action_id)){
            query.addCriteria(Criteria.where("action_id").is(action_id));
        }
        YcAction ycAction = mongoTemplate.findOne(query,YcAction.class);
        return ycAction;
    }
}
