package com.mytian.data.extension.controller;

import com.mytian.data.MytDataProcessApplication;
import com.mytian.data.commom.ServiceResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/20
 * Time: 12:03
 * Description:
 */
@SpringBootTest(classes = MytDataProcessApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataProcessControllerTest {

    @Resource
    DataCenterController dataCenterController;

    @Test
    public void saveMytianData() throws ParseException {

        Map<String, Object> map = new HashMap <>();
        String ip = "192.168.18.31";
        String uuid = "0c3cc187-8359-4aac-b115-8c0fbe9cdbe2";
        String url = "www.mytian.com.cn/myhtml/propaganda/pc/rz.html";
        String link = "www.mytian.com.cn";
        String pkg_name = "com.mytian.appstore.rz";
        String channel = "smsem";
        String plat = "android";
        String pageId = "1";
        String keywordId = "25";
        String action_id = "2";
        String createTime = "1525881600";


        map.put("ip",ip);
        map.put("uuid",uuid);
        map.put("ip",ip);
        map.put("url",url);
        map.put("link",link);
        map.put("pkg_name",pkg_name);
        map.put("channel",channel);
        map.put("pageId",pageId);
        map.put("plat",plat);
        map.put("action_id",action_id);
        map.put("keywordId",keywordId);
        map.put("createTime",createTime);

        ServiceResult serviceResult = dataCenterController.saveMytianData(map);
        System.out.println(serviceResult.toString());
    }

    @Test
    public void getUrlRate() {
//        dataProcessController.getUrlRate("www.baidu.com?pkg_name=com.mytian.mgarden&channel=mytian&pageId=1");
    }
}