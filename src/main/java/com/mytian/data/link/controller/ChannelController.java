package com.mytian.data.link.controller;

import com.mytian.data.link.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/16
 * Time: 19:52
 * Description:下拉框获取数据库中channel列表
 */

@Log4j2
@RestController
public class ChannelController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public List<String> getAllChannel(){

        List<String> channelList = productService.getAllChannel();

        return channelList;
    }

}
