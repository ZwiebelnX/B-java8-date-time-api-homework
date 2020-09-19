package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

  public static long getDaysBetweenNextLaborDay(LocalDate date) {
    LocalDate laborDay = LocalDate.now().withMonth(5).withDayOfMonth(1);
    if (laborDay.isBefore(date)) {
      laborDay = laborDay.plus(1, ChronoUnit.YEARS);
    }
    return laborDay.toEpochDay() - date.toEpochDay();
  }
}
