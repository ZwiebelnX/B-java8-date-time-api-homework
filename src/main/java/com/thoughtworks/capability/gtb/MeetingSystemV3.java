package com.thoughtworks.capability.gtb;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 *   场景：
 *   a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 *   b:而我们在当前时区(北京时区)使用系统
 *   c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

  public static void main(String[] args) {
    String timeStr = "2020-10-01 14:30:00";

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 从字符串解析得到会议时间
    LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
    ZonedDateTime londonMeetingTime = meetingTime.atZone(ZoneId.of("Europe/London"));
    System.out.println("会议时间（伦敦）: " + londonMeetingTime.format(formatter));

    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(londonMeetingTime.toLocalDateTime())) {
      londonMeetingTime = londonMeetingTime.plusDays(1);
      // 格式化新会议时间
      ZonedDateTime chicagoMeetingTime = londonMeetingTime.withZoneSameInstant(ZoneId.of("America/Chicago"));
      System.out.println("当日会议已结束。下一次会议时间（芝加哥）为：" + chicagoMeetingTime.format(formatter));

    } else {
      System.out.println("会议暂未开始");
      Period period = Period.between(LocalDate.now(), londonMeetingTime.toLocalDate());
      System.out.println("距离会议开始还剩：" + period.getDays() + "天");
    }
  }
}
