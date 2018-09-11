package com.mytian.data.commom;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/27
 * Time: 17:05
 * Description:结果码枚举
 */
public class ServiceResult {
    private ResultCode resultCode;
    private Object result;
    private List<?> list;

    public ServiceResult() {
    }

    public ServiceResult(ResultCode code) {
        this.resultCode = code;
    }

    public ServiceResult(ResultCode code, Object result) {
        this.resultCode = code;
        this.result = result;
    }

    public ServiceResult(ResultCode code, List<?> list) {
        this.resultCode = code;
        this.list = list;
    }


    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public void setCode(ResultCode resultCode, String error) {
        resultCode.setEnMsg(error);
        this.resultCode = resultCode;
    }

    public Object getResult(){
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Map<String,Object> toMap() {
        if (resultCode.getStatus() == 200) {
            Map temp = new HashMap();
            temp.put("status", 200);
            if (this.list == null && this.result == null){
                temp.put("res", new HashMap<String, List<String>>(){
                    {
                        put("list", new ArrayList<String>(1));
                    }
                });
            }else {
                temp.put("res", this.result);
            }
            return temp;
        }
        return null;
    }

    public Map<String,Object> toList() {
        if (resultCode.getStatus() == 200) {
            Map<String,Object> temp = new HashMap <>();
            temp.put("status", 200);

            Map<String,Object> listJson = new HashMap();
            if (this.list == null || this.list.size() < 1){
                listJson.put("list", new ArrayList<>(1));
            }else {
                listJson.put("list", this.list);
            }
            temp.put("res", listJson);
            return temp;
        }
        return null;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
