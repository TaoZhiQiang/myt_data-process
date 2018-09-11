package com.mytian.data.yancheng.service.serviceImpl;

import com.mytian.data.MytDataProcessApplication;
import com.mytian.data.yancheng.entity.YcAction;
import com.mytian.data.yancheng.entity.YcPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 15:54
 * Description:盐城活动测试类
 */

@SpringBootTest(classes = MytDataProcessApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class YanChengServiceImplTest {
    @Autowired
    YanChengServiceImpl yanChengServiceImpl;

    @Test
    public void saveYCPage() {
        YcPage ycPage = new YcPage();
        ycPage.setPageId("01");
        ycPage.setPageName("show");
        yanChengServiceImpl.saveYCPage(ycPage);
    }

    @Test
    public void saveYCAction() {
        YcAction ycAction = new YcAction();
        ycAction.setAction_id("04");
        ycAction.setAction("投票");
        yanChengServiceImpl.saveYCAction(ycAction);
    }
}