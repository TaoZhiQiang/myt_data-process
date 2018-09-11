package com.mytian.data.link.controller;

import com.mytian.data.MytDataProcessApplication;
import com.mytian.data.link.mapper.cluster.LinkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/18
 * Time: 11:00
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MytDataProcessApplication.class)
public class LinkControllerTest {

    @Resource
    LinkMapper linkMapper;

    @Test
    public void getAllChannel() {
        List<String> link = linkMapper.getAllLink();
        System.out.println(link.size());
    }
}