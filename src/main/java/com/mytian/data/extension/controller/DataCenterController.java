package com.mytian.data.extension.controller;

import com.mytian.data.commom.ResultCode;
import com.mytian.data.commom.ServiceResult;
import com.mytian.data.data_exchange.Data;
import com.mytian.data.extension.entity.DataProcess;
import com.mytian.data.extension.service.DataProcessService;
import com.mytian.data.extension.service.serviceImpl.DataProcessServiceImpl;
import com.mytian.data.link.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author zhiqiang.tao
 * Date: 2018/4/27
 * Time: 16:22
 * Description:
 */

@RestController
public class DataCenterController {
    private static final Logger log = LoggerFactory.getLogger( DataProcessServiceImpl.class );
    Long currentTime = null;

    @Resource
    DataProcessService dataProcessService;

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/dataCenter", method = RequestMethod.POST)
    public ServiceResult saveMytianData(@RequestParam Map <String, Object> request) throws ParseException {

        DataProcess dataProcess = new DataProcess();

        if (request.get("keywordId").equals("")){
            return new ServiceResult(ResultCode.CODE_412);
        }
        dataProcess.setKeywordId( Long.valueOf(request.get("keywordId").toString()) );
        String keyword = productService.getKeyword(request.get("keywordId"));
        if (StringUtils.isBlank(keyword)){
            return new ServiceResult(ResultCode.CODE_404);
        }
        dataProcess.setKeyword(keyword);

        if (request.get("ip").equals("")){
            return new ServiceResult(ResultCode.CODE_402);
        }
        dataProcess.setIp(request.get("ip").toString());

        if (request.get("url").equals("")){
            return new ServiceResult(ResultCode.CODE_406);
        }
        dataProcess.setUrl(request.get("url").toString());

        if (request.get("plat").equals("")){
            return new ServiceResult(ResultCode.CODE_407);
        }
        dataProcess.setPlat(request.get( "plat" ).toString());


        //需要校验的参数
        if (request.get("uuid").equals("")){
            return new ServiceResult(ResultCode.CODE_401);
        }
        dataProcess.setUuid(request.get("uuid").toString());

//        else {
//            DataProcess data = dataProcessService.getuUuid( ( String ) request.get( "uuid" ) );
//            System.out.println(data);
//            if (data != null) {
//                return new ServiceResult( ResultCode.CODE_419 );
//            } else {
//                dataProcess.setUuid(request.get("uuid").toString());
//            }
//        }

        if (request.get("channel").equals("")){
            return new ServiceResult(ResultCode.CODE_408);
        }else{
            Integer id = productService.getChannelId( ( String ) request.get("channel") );
            if (id != null){
                dataProcess.setChannel(request.get("channel").toString());
            }else{
                return new ServiceResult(ResultCode.CODE_413);
            }

        }

        if (request.get("link").equals("")){
            return new ServiceResult(ResultCode.CODE_409);
        }else{
            Integer id = productService.getLinkId( ( String ) request.get("link") );
            if (id != null){
                dataProcess.setLink(request.get("link").toString());
            }else{
                return new ServiceResult(ResultCode.CODE_416);
            }
        }

        if (request.get("action_id").equals("")){
            return new ServiceResult(ResultCode.CODE_403);
        }else{
            Integer id = productService.getActionId( Integer.valueOf(request.get("action_id").toString() ));
            if (id != null){
                dataProcess.setAction_id(Integer.valueOf(request.get("action_id").toString()));
            }else{
                return new ServiceResult(ResultCode.CODE_417);
            }
        }

        if (request.get("pkg_name").equals("")){
            return new ServiceResult(ResultCode.CODE_410);
        }else {
            Integer id = productService.getPackgeId( ( String ) request.get( "pkg_name" ) );
            if (id != null) {
                dataProcess.setPkg_name( request.get( "pkg_name" ).toString() );
            } else {
                return new ServiceResult( ResultCode.CODE_414 );
            }
        }

        if (request.get("pageId").equals("")){
            return new ServiceResult(ResultCode.CODE_405);
        }else {
            String page = productService.getPageId( Integer.valueOf( request.get( "pageId" ).toString() ) );
            if (page != null) {
                dataProcess.setPageId( Integer.valueOf( request.get( "pageId" ).toString() ) );
            } else {
                return new ServiceResult( ResultCode.CODE_415 );
            }
        }


        //获取系统当前时间，精确到天
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c=Calendar.getInstance();
        c.setTime(new Date() );
        c.add(Calendar.DAY_OF_MONTH, 1);
        String dateSuffix = dateformat.format(c.getTime());
        currentTime = Data.currentTime(dateSuffix);

        //时间进行判断1525104000（2018.5.1开始）到当前时间结束
        if (request.get("createTime").equals("")){
            return new ServiceResult(ResultCode.CODE_411);
        }
        Long time = Long.valueOf(request.get("createTime").toString());
        if (time >= 1525104000 && time <= currentTime){
            dataProcess.setCreateTime(Long.valueOf(request.get("createTime").toString()));
        }else{
            return new ServiceResult( ResultCode.CODE_418 );
        }



        try {
            dataProcessService.saveDataProcess( dataProcess );
           log.info("数据采集成功------------------------------");
        } catch (Exception e) {
            log.info("save fail is:"+dataProcess.toString());
            return new ServiceResult(ResultCode.CODE_400);
        }
        return new ServiceResult(ResultCode.CODE_200, dataProcess.toString());

    }

}
