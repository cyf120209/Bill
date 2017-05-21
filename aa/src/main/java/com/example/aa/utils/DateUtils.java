package com.example.aa.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtils {
    //将时间戳转化为年月日
    public static String getYMD(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }

    // 获取当前日期
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 秒数转化为时间间隔
     */
    public static String get_time(int time) {

        int hour = time / 60 / 60;
        int min = time % (60 * 60) / 60;
        int sec = time % (60 * 60) % 60;
        return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
    }

    Calendar cald;

    public String mDateStringFormat = "yyyy-MM-dd";
    public String mTimeStringFormat = "HH:mm:ss";
    public String mDateTimeStringFormat = "yyyy-MM-dd HH:mm:ss";
    public final static long ONE_DAY_MILLISECONDS = 86400000;

    /**
     * 以当前时间，建立一个DateUtil
     */
    public DateUtils() {
        cald = GregorianCalendar.getInstance();
    }

    /**
     * 以给定的long型的时间戳，建立一个DateUtil，这个时间戳是和本地time zone 无关的
     *
     * @param time
     */
    public DateUtils(long time) {
        cald = GregorianCalendar.getInstance();
        setTime(time);
    }


    /**
     * 用字符串时间来建立一个DateUtil
     *
     * @param dt
     * @throws ParseException
     */
    public DateUtils(String dt) throws ParseException {
        cald = GregorianCalendar.getInstance();
        setTime(dt);
    }

    /**
     * 用给定的时间，建立一个dateUtil类
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     */
    public DateUtils(int year, int month, int day, int hour, int minute, int second) {
        cald = GregorianCalendar.getInstance();
        setTime(year, month, day, hour, minute, second);
    }

    /**
     * 用一个时间戳来设定当前的DateUtil，，这个时间戳是和本地time zone 无关的
     *
     * @param time
     */
    public void setTime(long time) {
        Date datetime = new Date(time);
        cald.setTime(datetime);
    }

    /**
     * 用一个字符串时间来设定DateUtil的时间，该字符串必须符合一定的格式，缺省的
     * 格式是yyyy-MM-dd hh:mm:ss， 如果格式不一样，可以通过调用 setDateTimeStringFormat
     * 设定
     *
     * @param dt
     * @throws ParseException
     */
    public void setTime(String dt) throws ParseException {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(mDateTimeStringFormat);
        Date datetime = mDateFormat.parse(dt);
        cald.setTime(datetime);
    }

    /**
     * 设置年、月、日、时、分、秒，注意这些是local time zone 时间
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     */
    public void setTime(int year, int month, int day, int hour, int minute, int second) {
        cald.set(year, month, day, hour, minute, second);
    }

    /**
     * 设置年、月、日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDateTime(int year, int month, int day) {
        cald.set(year, month, day);
    }


    /**
     * 设置时、分、秒
     *
     * @param hour
     * @param minute
     * @param second
     */
    public void setTimeTime(int hour, int minute, int second) {
        cald.set(Calendar.HOUR_OF_DAY, hour);
        cald.set(Calendar.MINUTE, minute);
        cald.set(Calendar.SECOND, second);
    }


    /**
     * 设置时、分
     *
     * @param hour
     * @param minute
     * @param second
     */
    public void setShortTimeTime(int hour, int minute) {
        cald.set(Calendar.HOUR, hour);
        cald.set(Calendar.MINUTE, minute);
    }

    public String getDateString() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(mDateStringFormat);
        return mDateFormat.format(cald.getTime());
    }

    public String getTimeString() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(mTimeStringFormat);
        return mDateFormat.format(cald.getTime());
    }


    /**
     * 返回unix时间戳，这个是和time zone 没有关系的
     *
     * @return
     */
    public long getMillsecond() {
        return cald.getTime().getTime();
    }

    public int getYear() {
        return cald.get(Calendar.YEAR);
    }

    public int getMonth() {
        return cald.get(Calendar.MONTH);
    }

    public int getDay() {
        return cald.get(Calendar.DAY_OF_MONTH);
    }

    public int getWeek() {
        return cald.get(Calendar.DAY_OF_WEEK);
    }

    public int getHour() {
        return cald.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return cald.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return cald.get(Calendar.SECOND);
    }


    public void setDateStringFormat(String dsf) {
        mDateStringFormat = dsf;
    }

    public void setTimeStringFormat(String tsf) {
        mTimeStringFormat = tsf;
    }

    public void setDateTimeStringFormat(String tsf) {
        mDateTimeStringFormat = tsf;
    }

    public long getDayStartTick() {
        setTimeTime(0, 0, 0);
        return ((long) (getMillsecond() / 1000.0)) * 1000;//去掉小于一秒造成的不同
    }


    public long getDayEndTick() {
        return getDayStartTick() + ONE_DAY_MILLISECONDS;
    }

    public float getLiveDay(long birthday) {
        long curr = System.currentTimeMillis();
        float days = (curr - birthday) / (24 * 60 * 60 * 1000);
        return days;
    }

    public long getTime() {
        return cald.getTime().getTime();
    }

    public long getDiffTime(int year, int month, int day, int hour, int minute, int second) {
        int y = getYear() - year;
        int mo = getYear() - month;
        int d = getYear() - day;
        int h = getYear() - hour;
        int mi = getYear() - minute;
        int s = getYear() - second;
        int t = (((d) * 24) * 60 + mi) * 60 + s;
        return t;
    }

    public int getDayOfYear() {
        return cald.get(Calendar.DAY_OF_YEAR);
    }
}
