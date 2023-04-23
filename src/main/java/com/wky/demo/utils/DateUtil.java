package com.wky.demo.utils;

import com.wky.demo.exception.GlobalRuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

/**
 * @author: wangkunyang
 * @date 2021/12/22 17:03
 */
public class DateUtil {

    public static DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter FORMATTER_YEAR_MONTH_CHINESE = DateTimeFormatter.ofPattern("yyyy年MM月");
    public static DateTimeFormatter FORMATTER_YEAR_MONTH = DateTimeFormatter.ofPattern("yyyy-MM");
    public static String YEAR_CHINESE = "年";
    public static String MONTH_CHINESE = "月";
    public static LocalTime TIME_MAX=LocalTime.parse("23:59:59");

    /**
     * 获取指定日期对应的年月
     * 格式：yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getDateYm(LocalDate date) {
        String format = date.format(DateTimeFormatter.ISO_DATE);
        return format.substring(0, 7);
    }

    /**
     * 获取指定日期对应的年月
     * 格式：yyyy-MM
     *
     * @param date
     * @return
     */
    public static String getDateYm(LocalDateTime date) {
        String format = date.format(DateTimeFormatter.ISO_DATE);
        return format.substring(0, 7);
    }

    /**
     * 获取指定日期对应的年月日
     * 格式：yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateYmd(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * 将字符串转换成LocalDateTime
     *
     * @param strDate yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        return LocalDateTime.parse(strDate, FORMATTER_DATE_TIME);
    }

    /**
     * 将LocalDateTime转换成字符串
     *
     * @param dateTime
     * @return
     */
    public static String localDateTime2Str(LocalDateTime dateTime) {
        if (Objects.isNull(dateTime)) {
            return null;
        }
        return dateTime.format(FORMATTER_DATE_TIME);
    }

    /**
     * 获取本日之间的差值
     *
     * @param
     * @return
     */
    public static LocalTime getDataDifference(LocalDateTime start, LocalDateTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return null;
        }
        Long startSecond = start.toEpochSecond(ZoneOffset.of("+8"));
        Long endSecond = end.toEpochSecond(ZoneOffset.of("+8"));
        return LocalTime.ofSecondOfDay(Math.abs(startSecond - endSecond));
    }

    /**
     * 将字符串转换成LocalDateTime
     *
     * @param strDate yyyy-MM-dd
     * @return
     */
    public static LocalDate str2LocalDate(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        return LocalDate.parse(strDate, FORMATTER_DATE);
    }

    /**
     * 将 2022-06转换为2022年6月
     *
     * @param yearMonth
     * @return
     */
    public static String convert(String yearMonth) {
        String[] split = yearMonth.split("-");
        if (split.length != 2) {
            throw new GlobalRuntimeException(400, "年月日期格式错误");
        }
        String year = split[0];
        String month = split[1];
        String zero = "0";
        if (month.startsWith(zero)) {
            month = month.substring(1) + MONTH_CHINESE;
        }
        return year + YEAR_CHINESE + month;
    }


    public static LocalDate formatLocalDate(String dateStr) {
        DateTimeFormatter df = FORMATTER_DATE;
        LocalDate date = LocalDate.parse(dateStr, df);
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static Integer localDate2Integer(LocalDate date) {
        String format = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return Integer.valueOf(format);
    }

    /**
     * LocalDateTime 转 timestamp
     *
     * @param date .
     * @return Long
     */
    public static Long localDateTime2Timestamp(LocalDateTime date) {
        return date.toInstant(ZoneOffset.of("+8")).getEpochSecond();
    }

    /**
     * 获取时间之间的差值
     *
     * @param start .
     * @param end   .
     * @return Long
     */
    public static Long getDataDifferenceSecond(LocalDateTime start, LocalDateTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            throw new GlobalRuntimeException(400, "请输入正确的开始时间 或 结束时间！");
        }
        return localDateTime2Timestamp(end) - localDateTime2Timestamp(start);
    }

    /**
     * 接收的时间戳是秒级别的
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        return Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * 接收的时间戳是秒级别的
     *
     * @param timestamp
     * @return
     */
    public static LocalDate timestamp2LocalDate(long timestamp) {
        return Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

}
