package com.sailfish.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 日期工具类
 * @author summer
 * @date 2020.2.21
 */
public class DateUtils {
    /**
     * 计算时间差，单位为秒；如果小于1s，按1s计算
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间差，单位为秒
     */
    public static long calDuration(Date startDate, Date endDate) {
        return calDuration(startDate.getTime(), endDate.getTime());
    }

    public static long calDuration(long startDate, long endDate) {
        long msDuration = endDate - startDate;
        BigDecimal result = new BigDecimal(msDuration).divide(new BigDecimal(1000));
        return Double.valueOf(Math.ceil(result.doubleValue())).longValue();
    }

    public static void main(String[] args) {
        System.out.println(calDuration(3,5));
    }
}
