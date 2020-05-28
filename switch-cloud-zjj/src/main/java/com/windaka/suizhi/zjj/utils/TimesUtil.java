package com.windaka.suizhi.zjj.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @description: 验证开始时间和结束时间的格式是否正确
 * @author: hjt
 * @create: 2018-12-20 10:51
 * @version: 1.0.0
 **/

public class TimesUtil{
    /**
    * @Author: hjt
    * @Date: 2018/12/25
    * @Description: 验证当前时间格式是否符合 yyyy-MM-dd HH:mm:ss
    */
    public static boolean checkDateFormat(String str){
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        if(!StringUtils.isEmpty(str)){
            try {
                format.parse(str);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT_CHINESE = "yyyy年M月d日";

    /**
     * 获取当前日期
     *
     *
     * @return
     *
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(TimesUtil.DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     *
     * @return
     *
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(TimesUtil.DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     *
     * @return
     *
     */
    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String dateToDateTime(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(TimesUtil.DATE_TIME_FORMAT);
        datestr = df.format(date);
        return datestr;
    }
    /**
     * 将字符串日期转换为日期格式
     *
     *
     * @param datestr
     * @return
     *
     */
    public static Date stringToDate(String datestr) {

        if(datestr ==null ||datestr.equals("")){
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(TimesUtil.DATE_FORMAT);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            date=TimesUtil.stringToDate(datestr,"yyyyMMdd");
        }
        return date;
    }

    /**
     * 将字符串日期转换为日期格式
     * 自定義格式
     *
     * @param datestr
     * @return
     *
     */
    public static Date stringToDate(String datestr, String dateformat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    /**
     * 将日期格式日期转换为字符串格式
     *
     *
     * @param date
     * @return
     *
     */
    public static String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(TimesUtil.DATE_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 将日期格式日期转换为字符串格式 自定義格式
     *
     * @param date
     * @param dateformat
     * @return
     */
    public static String dateToString(Date date, String dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 获取日期的DAY值
     *
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getDayOfDate(Date date) {
        int d = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        d = cd.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    /**
     * 获取日期的MONTH值
     *
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getMonthOfDate(Date date) {
        int m = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        m = cd.get(Calendar.MONTH) + 1;
        return m;
    }

    /**
     * 获取日期的YEAR值
     *
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getWeekOfDate(Date date) {
        int wd = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
        return wd;
    }

    /**
     * 获取输入日期的当月第一天
     *
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 判断是否是闰年
     *
     *
     * @param date
     *      输入日期
     * @return 是true 否false
     *
     */
    public static boolean isLeapYEAR(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int year = cd.get(Calendar.YEAR);

        if (year % 4 == 0 && year % 100 != 0 | year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据整型数表示的年月日，生成日期类型格式
     *
     * @param year
     *      年
     * @param month
     *      月
     * @param day
     *      日
     * @return
     *
     */
    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month-1, day);
        return cd.getTime();
    }

    /**
     * 获取年周期对应日
     *
     * @param date
     *      输入日期
     * @param iyear
     *      年数  負數表示之前
     * @return
     *
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取月周期对应日
     *
     * @param date
     *      输入日期
     * @param i
     * @return
     *
     */
    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.MONTH, i);

        return cd.getTime();
    }

    /**
     * 计算 fromDate 到 toDate 相差多少年
     *
     * @param fromDate
     * @param toDate
     * @return 年数
     *
     */
    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df=Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt=Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR)-df.get(Calendar.YEAR);
    }

    /**
     * 计算 fromDate 到 toDate 相差多少个月
     *
     * @param fromDate
     * @param toDate
     * @return 月数
     *
     */
    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df=Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt=Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR)*12+dt.get(Calendar.MONTH)-
                (df.get(Calendar.YEAR)*12+df.get(Calendar.MONTH));
    }

    /**
     * 计算 fromDate 到 toDate 相差多少天
     *
     * @param fromDate
     * @param toDate
     * @return 天数
     *
     */
    public static long getDayByMinusDate(Object fromDate, Object toDate) {

        Date f=TimesUtil.chgObject(fromDate);

        Date t=TimesUtil.chgObject(toDate);

        long fd=f.getTime();
        long td=t.getTime();

        return (td-fd)/(24L * 60L * 60L * 1000L);
    }

    /**
     * 计算年龄
     *
     * @param birthday
     *      生日日期
     * @param calcDate
     *      要计算的日期点
     * @return
     *
     */
    public static int calcAge(Date birthday, Date calcDate) {

        int cYear=TimesUtil.getYearOfDate(calcDate);
        int cMonth=TimesUtil.getMonthOfDate(calcDate);
        int cDay=TimesUtil.getDayOfDate(calcDate);
        int bYear=TimesUtil.getYearOfDate(birthday);
        int bMonth=TimesUtil.getMonthOfDate(birthday);
        int bDay=TimesUtil.getDayOfDate(birthday);

        if(cMonth>bMonth||(cMonth==bMonth&&cDay>bDay)){
            return cYear-bYear;
        }else{
            return cYear-1-bYear;
        }
    }

    /**
     * 从身份证中获取出生日期
     *
     * @param idno
     *      身份证号码
     * @return
     *
     */
    public static String getBirthDayFromIDCard(String idno) {
        Calendar cd = Calendar.getInstance();
        if (idno.length() == 15) {
            cd.set(Calendar.YEAR, Integer.valueOf("19" + idno.substring(6, 8))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(8, 10))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(10, 12)).intValue());
        } else if (idno.length() == 18) {
            cd.set(Calendar.YEAR, Integer.valueOf(idno.substring(6, 10))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(10, 12))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(12, 14)).intValue());
        }
        return TimesUtil.dateToString(cd.getTime());
    }

    /**
     * 將OBJECT類型轉換為Date
     * @param date
     * @return
     */
    public static Date chgObject(Object date){

        if(date!=null&&date instanceof Date){
            return (Date)date;
        }

        if(date!=null&&date instanceof String){
            return TimesUtil.stringToDate((String)date);
        }

        return null;

    }

    public static long getAgeByBirthday(String date){

        Date birthday = stringToDate(date, "yyyy-MM-dd");
        long sec = new Date().getTime() - birthday.getTime();

        long age = sec/(1000*60*60*24)/365;

        return age;
    }
    /**
     * 获取指定日期类型的字符串时间
     *
     * @param Kind
     * @param currentTime
     * @return
     */
    public static String getServerDateTime(int Kind, Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String strDate = formatter.format(currentTime);
        StringTokenizer token = new StringTokenizer(strDate, "-");
        String year = token.nextToken();
        String month = token.nextToken();
        String day = token.nextToken();
        String hh = token.nextToken();
        String mm = token.nextToken();
        String ss = token.nextToken();
        String strServerDateTime = "";
        switch (Kind) {
            case 1:
                strServerDateTime = year + month + day;
                break;
            case 2:
                strServerDateTime = year + "-" + month + "-" + day + " " + hh;
                break;
            case 3:
                strServerDateTime = hh + mm + ss;
                break;
            case 4:
                strServerDateTime = hh + ":" + mm + ":" + ss;
                break;
            case 5:
                strServerDateTime = year + month + day + " " + hh + mm + ss;
                break;
            case 6:
                strServerDateTime = year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
                break;
            case 7:
                strServerDateTime = year + "-" + month + "-" + day + "|" + hh + ":" + mm + ":" + ss;
                break;
            case 8:
                strServerDateTime = year + month + day + hh + mm + ss;
                break;
            case 9:
                strServerDateTime = year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
                break;
            case 10:
                strServerDateTime = year + "-" + month + "-" + day;
                break;
            case 11:
                strServerDateTime = month + "月" + day + "日";
                break;
            default:
                break;
        }
        return strServerDateTime;
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


}
