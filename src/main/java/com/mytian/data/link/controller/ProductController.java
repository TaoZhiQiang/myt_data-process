package com.mytian.data.link.controller;

import com.mytian.data.extension.controller.DataProcessController;
import com.mytian.data.link.entity.Result;
import com.mytian.data.link.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/4/16
 * Time: 19:52
 * Description:
 * @author zhiqiang.tao
 */
@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger( DataProcessController.class );

    Integer pageId;
    Integer keyWordId;

    @Autowired
    ProductService productService;


    /**
     * 获取页面参数，生成对应的链接。
     */
    @RequestMapping(value = "/netWork", method = RequestMethod.POST)
    public List<Map<String,Object>> saveResult(String linkStr, String channelStr, String pkg_nameStr, String pageUrlStr,
                                               String keyword){
        List<Map<String,Object>> list = new ArrayList <>();

        String[] linkArr = linkStr.split(" ");
        String[] pkg_nameArr = pkg_nameStr.split(" ");
        String[] channelArr = channelStr.split(" ");
        String[] pageUrlArr = pageUrlStr.split(" ");

            for (int i = 0; i <linkArr.length ; i++) {

                for (int j = 0; j <pkg_nameArr.length; j++) {

                    for (int n = 0; n < channelArr.length; n++) {

                        for (int m = 0; m < pageUrlArr.length; m++) {

                            //主域名后具体模块路径
                            String path = productService.getPath(linkArr[i]);
                            //落地页页码
                            String page = productService.getPage(pageUrlArr[m]);
                            //落地页id
                            pageId = productService.getIdByPage(pageUrlArr[m]);

                                //将keyWord关键字切割（比较坑的是只能用中文“，”）
                                String[] keyWordArr = keyword.split("，");

                                for (String keyWords:keyWordArr) {

                                    String pkg_name = productService.getPkgName(pkg_nameArr[j]);
                                    String html = productService.getHtml(pkg_nameArr[j]);

                                    //将前台传来的关键词保存，生成的id拼接上去(先查是否存在)
                                    if (StringUtils.isNotBlank(keyWords)){
                                        keyWordId = productService.getIdByWord(keyWords);
                                        if (keyWordId == null){
                                            productService.saveKeyWord(keyWords);
                                            keyWordId = productService.getIdByWord(keyWords);
                                        }
                                    }

                                    if (channelArr[n].equals("")){
                                        channelArr[n]=null;
                                    }

                                    Map<String,Object> map = new HashMap <>();
                                    map.put("link",linkArr[i]);
                                    map.put("pkg_name",pkg_nameArr[j]);
                                    map.put("channel",channelArr[n]);
                                    map.put("pageUrl",pageUrlArr[m]);
                                    map.put("keyword",keyWords);

                                    List<String> list1 = new ArrayList <>();
                                    list1.add(linkArr[i]+"/" + path + "/" + page + "/" + html + "&pkg_name=" + pkg_name
                                            + "&channel=" + channelArr[n] + "&ct=" + channelArr[n] + "&pageId=" + pageId
                                            + "&keywordId=" + keyWordId);

                                    for (int k = 0; k < list1.size(); k++) {

                                        map.put("result",list1.get(k));
                                        list.add(map);

                                        //将结果循环放入数据库
                                        Result result = new Result();
                                        result.setResult(list1.get(k));
                                        //查询数据库中是否存在
                                        Integer id = productService.getIdByResult(list1.get(k));
                                        if (id == null){
                                            try {
                                                productService.SaveResult(result);
                                            }catch  (Exception e) {
                                                // TODO Auto-generated catch block
                                                log.info("链接保存异常");
                                            }
                                        }else {
                                            log.info("该链接已存在");
                                        }
                                    }


                                }
                        }
                    }

                }

            }

        return list;
    }

    //判断是否为汉字
    static boolean word(String str) {

        Pattern p = compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


}
