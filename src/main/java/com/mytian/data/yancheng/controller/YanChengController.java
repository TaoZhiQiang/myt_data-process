package com.mytian.data.yancheng.controller;

import com.mytian.data.commom.ResultCode;
import com.mytian.data.commom.ServiceResult;
import com.mytian.data.data_exchange.Data;
import com.mytian.data.extension.service.serviceImpl.DataProcessServiceImpl;
import com.mytian.data.yancheng.entity.YanCheng;
import com.mytian.data.yancheng.entity.YcAction;
import com.mytian.data.yancheng.entity.YcPage;
import com.mytian.data.yancheng.service.YanChengService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 15:03
 * Description:盐城活动
 */
@RestController
public class YanChengController {

    private static final Logger log = LoggerFactory.getLogger( DataProcessServiceImpl.class );

    Long currentTime = null;

    @Autowired
    YanChengService yanChengService;

    @RequestMapping(value = "/yanCheng", method = RequestMethod.POST)
    public ServiceResult saveYanCheng(@RequestParam Map<String, Object> request) throws ParseException {

        YanCheng yanCheng = new YanCheng();

        if (request.get("openId").equals("")){
            return new ServiceResult(ResultCode.CODE_421);
        }
        yanCheng.setOpenId( ( String ) request.get("openId") );





        //需要校验的参数
        if (request.get("pageId").equals("")){
            return new ServiceResult(ResultCode.CODE_405);
        }else {
            YcPage ycPage = yanChengService.getPageName( ( String ) request.get( "pageId" ) );
            if (ycPage != null) {
                yanCheng.setPageId( ( String ) request.get("pageId") );
            } else {
                return new ServiceResult( ResultCode.CODE_422 );
            }
        }




        if (request.get("pageName").equals("")){
            return new ServiceResult(ResultCode.CODE_420);
        }else {
            YcPage ycPage = yanChengService.getPageId( ( String ) request.get( "pageName" ) );
            if (ycPage != null) {
                yanCheng.setPageName( ( String ) request.get("pageName") );
            } else {
                return new ServiceResult( ResultCode.CODE_422 );
            }
        }

        //同一个页面传递多个action
        if (request.get("action_id").equals("")){
            return new ServiceResult(ResultCode.CODE_403);
        }else {
            String actions = ( String ) request.get("action_id");
            List actionList = Arrays.asList( actions.split( "," ) );
            List<YcAction> ycActionList = new ArrayList <>();
            for (Object action_id:actionList) {
                YcAction ycAction = yanChengService.getAction( ( String ) action_id );
                //校验多个action_id是否全部合法
                if (ycAction == null){
                    return new ServiceResult( ResultCode.CODE_417 );
                }
                ycActionList.add(ycAction);
            }
            if (ycActionList != null) {
                yanCheng.setAction_id(actions);
            } else {
                return new ServiceResult( ResultCode.CODE_417 );
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
        }else{
            Long time = Long.valueOf(request.get("createTime").toString());
            if (time >= 1525104000 && time <= currentTime){
                yanCheng.setCreateTime(Long.valueOf(request.get("createTime").toString()));
            }else{
                return new ServiceResult( ResultCode.CODE_418 );
            }
        }


        try {
            yanChengService.saveYanCheng( yanCheng );
            log.info("数据采集成功------------------------------");
        } catch (Exception e) {
            log.info("save fail is:"+yanCheng.toString());
            return new ServiceResult(ResultCode.CODE_400);
        }


        return new ServiceResult(ResultCode.CODE_200,null);
    }
}
