package com.mytian.data.commom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/4/27
 * Time: 17:02
 * Description:响应结果类
 * @author zhiqiang.tao
 */
public enum ResultCode {

    CODE_200(200, 200, "success.", "响应成功"),
    CODE_400(400, 400, "save fail", "数据保存失败"),
    CODE_401(400, 401, "lack uuid", "缺少参数uuid"),
    CODE_402(400, 402, "lack ip", "缺少参数ip"),
    CODE_403(400, 403, "lack action_id", "缺少参数action_id"),
    CODE_404(400, 404, "lack keyword", "缺少参数keyword"),
    CODE_405(400, 405, "lack pageId", "缺少参数pageId"),
    CODE_406(400, 406, "lack url", "缺少参数url"),
    CODE_407(400, 407, "lack plat", "缺少参数plat"),
    CODE_408(400, 408, "lack channel", "缺少参数channel"),
    CODE_409(400, 409, "lack link", "缺少参数link"),
    CODE_410(400, 410, "lack pkg_name", "缺少参数pkg_name"),
    CODE_411(400, 411, "lack createTime", "缺少参数createTime"),
    CODE_412(400, 412, "lack keywordId", "缺少参数keywordId"),
    CODE_413(400, 413, "channel error", "渠道不存在"),
    CODE_414(400, 414, "App error", "App不存在"),
    CODE_415(400, 415, "pageUrl error", "落地页不存在"),
    CODE_416(400, 416, "link error", "域名不存在"),
    CODE_417(400, 417, "action error", "用户行为不存在"),
    CODE_418(400, 418, "time error", "时间不在统计时间内"),
    CODE_419(400, 419, "uuid error", "uuid已存在"),


    CODE_420(400, 420, "lack pageName", "缺少参数pageName"),
    CODE_421(400, 421, "lack openId", "缺少参数openId"),
    CODE_422(400, 422, "error pageId", "该页面不存在");


    private int code;
    private int status;
    private String enMsg;
    private String zhMsg;


    ResultCode(int code, int status, String enMsg, String zhMsg) {
        this.code = code;
        this.status = status;
        this.enMsg = enMsg;
        this.zhMsg = zhMsg;
    }

    public Map<String,Object> toJMap(ResultCode ResultCode){
        Map<String,Object> json = new HashMap <>();
        json.put("status", ResultCode.getStatus());
        json.put("error",ResultCode.getEnMsg());
        return json;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEnMsg() {
        return enMsg;
    }

    public void setEnMsg(String enMsg) {
        this.enMsg = enMsg;
    }

    public String getZhMsg() {
        return zhMsg;
    }

    public void setZhMsg(String zhMsg) {
        this.zhMsg = zhMsg;
    }
}
