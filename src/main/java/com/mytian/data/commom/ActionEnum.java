package com.mytian.data.commom;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Description: 用户活动枚举
 */
public enum ActionEnum {
    INTO(0,"进入"),VIEW(1, "浏览"), DOWNLOAD(2, "下载"),;

    private int value;
    private String desc;

    ActionEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static boolean checkStatisType(int type) {
        if (type == VIEW.getValue() || type == DOWNLOAD.getValue()) {
            return true;
        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

