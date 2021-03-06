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
 * Description:下拉框获取数数据库所有的主链接
 */

@Log4j2
//@Controller
@RestController
public class CategoryController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public List<String> getAllCategory(){
        List<String> category = productService.getAllCategory();
        return category;
    }

}
