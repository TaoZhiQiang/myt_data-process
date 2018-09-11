package com.mytian.data.yancheng.controller;

import com.mytian.data.MytDataProcessApplication;
import com.mytian.data.commom.ServiceResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 18:11
 * Description:
 */
@SpringBootTest(classes = MytDataProcessApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class YanChengControllerTest {

    @Autowired
    YanChengController yanChengController;

    @Test
    public void saveYanCheng() throws ParseException {

        Map<String, Object> map = new HashMap<>();

        String openId = "333333333";
        String pageId = "01";
        String pageName = "share";
        String action_id = "0100,0102,0109";
        String createTime = "1525881600";

        map.put("openId",openId);
        map.put("pageId",pageId);
        map.put("pageName",pageName);
        map.put("action_id",action_id);
        map.put("createTime",createTime);


        ServiceResult serviceResult = yanChengController.saveYanCheng(map);
        System.out.println(serviceResult.toString());
    }
}