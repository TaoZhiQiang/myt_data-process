package com.mytian.data.yancheng.service;

import com.mytian.data.yancheng.entity.YanCheng;
import com.mytian.data.yancheng.entity.YcAction;
import com.mytian.data.yancheng.entity.YcPage;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhiqiang.tao
 * @date: 2018/5/10
 * @time: 15:06
 * Description:
 */
public interface YanChengService {
    void saveYanCheng(YanCheng yanCheng);

    YcPage getPageName(String pageId);

    YcPage getPageId(String pageName);

    YcAction getAction(String action_id);
}
