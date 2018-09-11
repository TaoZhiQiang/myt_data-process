package com.mytian.data.data_exchange;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * @author: zhiqiang.tao
 * Description: date转换
 */
public class Data {

    public static void main(String[] args) throws ParseException {
        //参数
        String res = "2018-04-22";
//        stampToDate(res);

        String sDateSuffix = getTimeByDate();

        //时间转为时间戳
        try {
            dateStart(sDateSuffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        System.out.println(res);
        return res;
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        System.out.println(res);
        return res;
    }


     // 将时间转换为时间戳(非精确)
    public static String dateStart(String s) throws Exception {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        res = String.valueOf(ts);
        return res;
    }

    public static String dateEnd(String s) throws Exception {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = (date.getTime()/1000)+86400;
        res = String.valueOf(ts);
        return res;
    }

    public static Long currentTime(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long currentTime = (date.getTime()/1000)-1;
        return currentTime;
    }

    public static String getTimeByDate() {
        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String sDateSuffix = dateformat.format(date);
        return sDateSuffix;
    }

   
}