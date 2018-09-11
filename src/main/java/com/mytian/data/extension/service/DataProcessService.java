package com.mytian.data.extension.service;

import com.mytian.data.extension.entity.DataProcess;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Date: 2018/4/19
 * Time: 17:33
 * Description:
 */
public interface DataProcessService {

     void saveDataProcess(com.mytian.data.extension.entity.DataProcess dataProcess);

     long findViewCount(String link, String pkg_name, String channel, String keyword,String start,String end, String url,
                        String plat);

     long findDownloadCount(String link, String pkg_name, String channel, String keyword,String start,String end,
                            String url,String plat);

     List<String> findExcludeKeyword(String link, String pkg_name, String channel, String start, String end);

     List<DataProcess> findDataProcessByKeyWords(String link, String pkg_name, String channel, String start, String end,String keyword);

     List<DataProcess> findDataProcessByKeyWord(String link, String pkg_name, String channel, String keyword, String start, String end);

     DataProcess findDataProcessByUrls(String link, String pkg_name, String channel, String keyword,String start,String end, String url);

    DataProcess getuUuid(String uuid);
}
