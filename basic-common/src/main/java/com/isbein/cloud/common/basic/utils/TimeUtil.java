package com.isbein.cloud.common.basic.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeUtil {

    public static String getWpayTime() {
        return getWpayTime(new Date());
    }

    public static String getWpayTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    //时间转换成unix格式
    //param:format "yyyy-MM-dd" 任何时间格式都可以
    public static Long timeToUnixFromSecond(String time, String format) {
        long unixTime;
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = sf.parse(time);
            unixTime = date.getTime();
            return unixTime /1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }


    /**
     * 计算天时分
     *
     * @param diff
     * @return
     * @throws ParseException
     */
    public static Map<String, Long> timestampDiff(long diff) throws ParseException {
        long day = diff / (3600 * 24);
        long hour = diff % (3600 * 24) / 3600;
        long minute = diff % 3600 / 60;
        Map<String, Long> map = new HashMap<>();
        map.put("day", day);
        map.put("hour", hour);
        map.put("minute", minute);
        return map;
    }

    public static long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    //unix转换成时间
    //format: "yyyyMMdd HH:mm:ss"
    public static String unixToTime(long UnixTimestamp, String format) {
        String date = new SimpleDateFormat(format).format(new Date(UnixTimestamp * 1000));
        return date;
    }

    public static Long getCurrentUnixTime() {
        long UnixTimestamp = System.currentTimeMillis() / 1000L;
        return UnixTimestamp;
    }

    //获取一个月的第一天
    public static String getFirstOfMonth() {
        String startTime;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        startTime = sf.format(cal.getTime());
        return startTime;
    }

    //获取上个月的第一天
    public static String getFirstofLastMonth() {
        String firstDayofMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        firstDayofMonth = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return firstDayofMonth;
    }

    //获取一个月的最后一天
    public static String getLastOfMonth() {
        String endTime;
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        endTime = format.format(ca.getTime());
        return endTime;
    }

    //获取上个月的最后一天
    public static String getlastofLastMonth() {
        String lastDayofMonth;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        lastDayofMonth = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        return lastDayofMonth;
    }

    //获取阶段性日期
    public static String getPeriodDate(long dateline) {
        long minute = 60;// 1分钟
        long hour = 60 * minute;// 1小时
        long day = 24 * hour;// 1天
        long month = 31 * day;// 月
        long year = 12 * month;// 年

        if (dateline == 0) {
            return null;
        }
        long diff = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10)) - dateline;
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static String Days(String enter_time, String exit_time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
//        enter_time ="2018-2-2 17:06:34";
//        exit_time = "2018-2-2 19:25:16";
        //将截取到的时间字符串转化为时间格式的字符串
        Date beginTime = sdf.parse(enter_time);
        Date endTime = sdf.parse(exit_time);
        //默认为毫秒，除以1000是为了转换成秒
        long interval = (endTime.getTime() - beginTime.getTime()) / 1000;//秒
        long day = interval / (24 * 3600);//天
        long hour = interval % (24 * 3600) / 3600;//小时
        long minute = interval % 3600 / 60;//分钟
        long second = interval % 60;//秒
        //System.out.println("两个时间相差：" + day + "天" + hour + "小时" + minute + "分" + second + "秒");
        String days = day + "天" + hour + "小时" + minute + "分" + second + "秒";
        return days;
    }

    public static String DaysWithUnixTime(Long enter_time, Long exit_time) {
        //默认为毫秒，除以1000是为了转换成秒
        long interval = exit_time - enter_time;//秒
        long day = interval / (24 * 3600);//天
        long hour = interval % (24 * 3600) / 3600;//小时
        long minute = interval % 3600 / 60;//分钟
        long second = interval % 60;//秒
        String days;
        if (day <= 0) {
            days = hour + "小时" + minute + "分" + second + "秒";
        } else if (hour == 0) {
            days = day + "天" + hour + "小时" + minute + "分" + second + "秒";
        } else {
            days = day + "天" + hour + "小时" + minute + "分" + second + "秒";
        }
        return days;
    }

    public static String DaysWithUnixTime(Integer enter_time, Integer exit_time) {
        //默认为毫秒，除以1000是为了转换成秒
        long interval = exit_time - enter_time;//秒
        long day = interval / (24 * 3600);//天
        long hour = interval % (24 * 3600) / 3600;//小时
        long minute = interval % 3600 / 60;//分钟
        long second = interval % 60;//秒
        String days;
        if (day <= 0) {
            if (hour <= 0) {
                days = minute + "分" + second + "秒";
            } else {
                days = hour + "小时" + minute + "分" + second + "秒";
            }
        } else {
            days = day + "天" + hour + "小时" + minute + "分" + second + "秒";
        }
        return days;
    }

    public static String DateToWeek(Date date) {
        String[] WEEK = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int WEEKDAYS = 7;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    /**
     * 通过unix计算当天0点时间
     * @param unixNowTime
     * @return
     */
    public static Long getTodayBeginTime(Long unixNowTime) {
        return (unixNowTime - (unixNowTime + 8 * 3600) % 86400);
    }


    public static void main(String[] args) throws ParseException {
//        System.out.println(beginToEndWithSec("2019-04-09 16:00:00", "2019-04-09 16:06:40"));
        System.out.println(getTodayBeginTime(Long.valueOf("1577171456")));
    }



    /**
     * 获取今天00点00分00秒的dateline
     *
     * @return
     */
    public static Long getTodayBeginTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }



    public static String formatDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String dateline1 = String.valueOf(date);
        String dateline = simpleDateFormat.format(new Date(Integer.parseInt(dateline1) * 1000L));
        return dateline;
    }

    //验证正则表达式邮箱是否合法
    public static boolean isEmail(String email) {
        //正则表达式
  /*
    String regex = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
    return email.matches(regex);
   */

        //不适用正则
        if (email == null || "".equals(email)) return false;
        if (!containsOneWord('@', email) || !containsOneWord('.', email)) return false;
        String prefix = email.substring(0, email.indexOf("@"));
        String middle = email.substring(email.indexOf("@") + 1, email.indexOf("."));
        String subfix = email.substring(email.indexOf(".") + 1);
        System.out.println("prefix=" + prefix + "  middle=" + middle + "  subfix=" + subfix);
        if (prefix == null || prefix.length() > 40 || prefix.length() == 0) return false;
        if (!isAllWords(prefix)) return false;
        if (middle == null || middle.length() > 40 || middle.length() == 0) return false;
        if (!isAllWordsAndNo(middle)) return false;
        if (subfix == null || subfix.length() > 3 || subfix.length() < 2) return false;
        if (!isAllWords(subfix)) return false;
        return true;
    }

    //判断字符串只包含指定的一个字符c
    private static boolean containsOneWord(char c, String word) {
        char[] array = word.toCharArray();
        int count = 0;
        for (Character ch : array) {
            if (c == ch) {
                count++;
            }
        }
        return count == 1;
    }

    //检查一个字符串是否全部是字母
    private static boolean isAllWords(String prefix) {
        char[] array = prefix.toCharArray();
        for (Character ch : array) {
            if (ch < '0' || ch > 'z') return false;
            else if (ch > '9' && ch < 'A') return false;
            else if (ch > 'Z' && ch < 'a') return false;
        }
        return true;
    }

    //检查一个字符串是否包含字母和数字
    private static boolean isAllWordsAndNo(String middle) {
        char[] array = middle.toCharArray();
        for (Character ch : array) {
            if (ch < '0' || ch > 'z') return false;
            else if (ch > '9' && ch < 'A') return false;
            else if (ch > 'Z' && ch < 'a') return false;
        }

        return true;
    }

    public static String getAfterHalfHour() {
        long curren = System.currentTimeMillis();
        curren += 30 * 60 * 1000;
        Date da = new Date(curren);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return dateFormat.format(da);
    }

    public static Long getAfterHalfHourWithUnix() {
        long curren = System.currentTimeMillis();
        curren += 30 * 60 * 1000;
        return curren / 1000;
    }


    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分";
    }

    /**
     * 计算两个时间之间的差 用小时。分钟来表示
     *
     * @param begin 开始的时间戳
     * @param end   结束的时间戳
     * @return “x时x分” 精确到分
     * @throws ParseException 转换异常
     */
    public static String beginToEndWithMin(long begin, long end) throws ParseException {
        long between = (end - begin);//除以1000是为了转换成秒
        long hour = between / 3600;
        long minute = between % 3600 / 60;
        if (hour == 0 && minute == 0) {
            minute = 1;
        }
        return hour + "小时" + minute + "分钟";
    }


    public static Long beginToEndWithSec(String begin, String end) {
        Long beginUnix = timeToUnixFromSecond(begin, "yyyy-MM-dd HH:mm:ss");
        Long endUnix = timeToUnixFromSecond(end, "yyyy-MM-dd HH:mm:ss");
        long between = (endUnix - beginUnix);//除以1000是为了转换成秒
        return between;
    }

    /**
     * 计算两个时间之间的差 用小时。分钟来表示
     *
     * @param begin 开始的时间戳
     * @param end   结束的时间戳
     * @return “x时x分” 精确到秒
     * @throws ParseException 转换异常
     */
    public static Long beginToEndWithSec(long begin, long end) throws ParseException {
        long between = (end - begin);//除以1000是为了转换成秒
        return between;
    }

    /**
     * 计算当前时间是是否在两个时间段的区间内
     *
     * @param nowTime   当前时间
     * @param beginTime 开始的时间
     * @param endTime   结束的时间
     * @return “x时x分” 精确到秒
     * @throws ParseException 转换异常
     */
    public static boolean belongCalendar(Long nowTime, Long beginTime, Long endTime) {
        if (endTime > nowTime && nowTime > beginTime) {
            return true;
        }
        return false;

    }




    public static Date unixToDate(Long unix) throws ParseException {
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataTmp = sdfIn.parse(unixToTime(unix,"yyyy-MM-dd HH:mm:ss"));
        return dataTmp;
    }
    /*public static Date dateToTime(Date date,String format) throws ParseException {
        SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataTmp = sdfIn.parse(unixToTime(unix,"yyyy-MM-dd HH:mm:ss"));
        return dataTmp;
    }*/
    public static String getDataTime(){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        return date;
    }




    public static String dateToTime(Date date,String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

}
