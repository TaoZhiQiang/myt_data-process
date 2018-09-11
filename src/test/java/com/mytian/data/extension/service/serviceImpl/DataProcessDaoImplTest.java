package com.mytian.data.extension.service.serviceImpl;

import com.mytian.data.MytDataProcessApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/26
 * Time: 18:19
 * Description:
 */

@SpringBootTest(classes = MytDataProcessApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataProcessDaoImplTest {


    @Autowired
    DataProcessServiceImpl dataProcessDaoImpl;

    @Test
    public void getApp() {
        String pkg_name = "com.mytian.rz";
//        dataProcessDaoImpl.getApp(pkg_name);
    }

//    @Test
//    public void saveApp() {
//        App app = new App();
//        app.setPkg_name("com.mytian.rz");
//        app.setName("麦田认字");
//        dataProcessDaoImpl.saveApp(app);
//    }
}