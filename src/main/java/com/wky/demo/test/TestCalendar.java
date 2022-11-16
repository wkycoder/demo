package com.wky.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author wangkunyang
 * @date 2022/06/02 13:46
 */
public class TestCalendar {

    public static void main(String[] args) throws ParseException {

        String date = "2022-05-05";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(date));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        SimpleDateFormat ddd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = ddd.format(calendar.getTime());
        System.out.println(format);
        System.out.println(sdf.parse(sdf.format(calendar.getTime())));;
    }

}
