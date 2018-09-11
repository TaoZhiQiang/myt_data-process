package com.mytian.data.extension.controller;

import com.mytian.data.data_exchange.Data;
import com.mytian.data.link.service.impl.ProductServiceImpl;
import com.mytian.data.extension.entity.DataProcess;
import com.mytian.data.extension.service.DataProcessService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/4/19
 * Time: 18:12
 * Description:数据展示页面接口
 * @author zhiqiang.tao
 */

@RestController
public class DataProcessController {
    private static final Logger log = LoggerFactory.getLogger( DataProcessController.class );

    Map<String,Object> map = null;
    String rate;
    String start = "";
    String end = "";

    @Resource
    DataProcessService dataProcessService;

    @Resource
    ProductServiceImpl productServiceImpl;

    /**
     * Created with IntelliJ IDEA.
     * @author: zhiqiang.tao
     * Description: 下载率排序
     */
    @RequestMapping(value = "/getRate", method = RequestMethod.POST)
    public List<Map<String,Object>> getUrlRate(String startDate, String endDate, String link, String appName,
                                               String channel, String keyword) throws Exception {

        String pkg_name = productServiceImpl.getPkg_name(appName );

        List <Map <String, Object>> list = getMaps( startDate, endDate, link, pkg_name, channel, keyword );

        if (list.size()>1){
            Collections.sort(list, (o1, o2) -> {
                Double name1 = Double.valueOf( Double.valueOf(o1.get("rate").toString()) );
                Double name2 = Double.valueOf( Double.valueOf(o2.get("rate").toString()) );
                return name2.compareTo(name1);
            } );
        }

        //过滤重复元素
        for (int i = 0; i < list.size() - 1; i++ ){
            for (int j = list.size() - 1; j > i; j-- ){
                if (list.get(j).equals(list.get(i))){
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * Created with IntelliJ IDEA.
     * @author: zhiqiang.tao
     * Description: 浏览量排序
     */
    @RequestMapping(value = "/getView", method = RequestMethod.POST)
    public List<Map<String,Object>> getViewCount(String startDate, String endDate, String link, String appName,
                                                 String channel, String keyword) throws Exception {

        String pkg_name = productServiceImpl.getPkg_name(appName );

        List <Map <String, Object>> list = getMaps( startDate, endDate, link, pkg_name, channel, keyword );

        if (list.size()>1){
            Collections.sort(list, (o1, o2) -> {
                Integer name1 = Integer.valueOf( Integer.valueOf(o1.get("count").toString()));
                Integer name2 = Integer.valueOf( Integer.valueOf(o2.get("count").toString()));
                return name2.compareTo(name1);
            } );
        }

        //过滤重复元素
        for (int i = 0; i < list.size() - 1; i++ ){
            for (int j = list.size() - 1; j > i; j-- ){
                if (list.get(j).equals(list.get(i))){
                    list.remove(j);
                }
            }
        }
        return list;
    }


    //处理返回值
    public List <Map <String, Object>> getMaps(String startDate, String endDate, String link, String pkg_name,
                                               String channel, String keyword) throws Exception {
        List <Map<String,Object>> list = new ArrayList<>();
        //转换时间
        try {
            if (startDate != ""){
                start = Data.dateStart(startDate);
            }
            if (endDate != ""){
                end = Data.dateEnd(endDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(keyword)){
            //依据关键字获取所有需要的数据
            List<DataProcess> dataProcessList = dataProcessService.findDataProcessByKeyWord(link, pkg_name, channel,
                    keyword, start, end);

            List<String> urlList = new ArrayList <>();
            for (DataProcess dataProcess:dataProcessList) {
                urlList.add(dataProcess.getUrl());
            }

            //封装展示数据
            viewDataProcess(list, keyword,start, end, dataProcessList );

        }else{

            List<String> keyWordList = dataProcessService.findExcludeKeyword(link, pkg_name, channel, start, end);

            //循环关键字
            for (String keyWords:keyWordList) {

                List<DataProcess> dataProcessList = dataProcessService.findDataProcessByKeyWords(link, pkg_name, channel,
                        start, end, keyWords);

                List<String> urlList = new ArrayList <>();
                for (DataProcess dataProcess:dataProcessList) {
                    urlList.add(dataProcess.getUrl());
                }

                //封装展示数据
                viewDataProcess(list, keyWords,start, end, dataProcessList );

            }
        }
        return list;
    }


    //展示数据
    private void viewDataProcess(List<Map<String,Object>> list, String keyword, String start, String end,
                                 List<DataProcess> dataProcessList) {

        for (DataProcess dataProcess:dataProcessList) {
            map = new ConcurrentHashMap<>();

            if (dataProcess.getPageId() != null){
                String name = productServiceImpl.getName(dataProcess.getPageId());
                if (name !=null ){
                    map.put("pageUrl",name);
                }
            }

            if (StringUtils.isNotBlank(dataProcess.getPkg_name())){
                String appName = productServiceImpl.getName(dataProcess.getPkg_name() );
                if (appName != null ){
                    map.put("App",appName);
                }
            }

            map.put("url",dataProcess.getUrl());
            map.put("plat",dataProcess.getPlat());
            map.put("link",dataProcess.getLink());
            map.put("channel",dataProcess.getChannel());
            map.put("keyWord",keyword);

            //浏览数
            long viewCount = dataProcessService.findViewCount(dataProcess.getLink(), dataProcess.getPkg_name(),
                    dataProcess.getChannel(), dataProcess.getKeyword(), start, end, dataProcess.getUrl(),dataProcess.getPlat());

            //下载数
            long downloadCount = dataProcessService.findDownloadCount(dataProcess.getLink(), dataProcess.getPkg_name(),
                    dataProcess.getChannel(), dataProcess.getKeyword(), start, end, dataProcess.getUrl(),dataProcess.getPlat());
            map.put("downloadCount", downloadCount);
            map.put("count",viewCount);

            //调用计算方法
            map = checkResult(viewCount,downloadCount);
            list.add(map);
        }
    }


    public Map<String, Object> checkResult(long viewCount,long downloadCount) {

        //判断下载率为零时
        if (downloadCount == 0) {
            rate = "0";
            map.put("rate", rate);
        } else {
            if (viewCount == 0) {
                rate = "100";
                map.put("rate", rate);
            } else {
                //计算下载率
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                rate = numberFormat.format(( float ) downloadCount / ( float ) viewCount * 100);
                map.put("rate", rate);
            }
        }

        return map;
    }
}