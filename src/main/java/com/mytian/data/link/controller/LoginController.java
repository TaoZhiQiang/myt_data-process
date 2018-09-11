package com.mytian.data.link.controller;

import com.mytian.data.link.entity.User;
import com.mytian.data.link.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/16
 * Time: 19:52
 * Description:下拉框获取数数据库所有的主链接
 */

@Log4j2
@Controller
public class LoginController {

    @Autowired
    ProductService productService;

    //显示登录界面
    @GetMapping("/")
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String loginPost1(@RequestParam String username,@RequestParam String password){
        login( username, password );
        return "redirect:develop.html";
    }

    @RequestMapping(value = "/disUser",method = RequestMethod.POST)
    public String loginPost2(@RequestParam String username,@RequestParam String password){
        login( username, password );
        return "redirect:display.html";
    }


    public void login(@RequestParam String username, @RequestParam String password) {
        if (username != null && password != null) {
            User user = productService.findByNameAndPassword(username);
            System.out.println(user);
            if (user == null){
                log.info("该用户不存在");
            }
        }
    }

}
