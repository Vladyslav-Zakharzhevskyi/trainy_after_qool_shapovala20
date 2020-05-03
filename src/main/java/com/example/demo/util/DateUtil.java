package com.example.demo.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DateUtil {

    public static Date currentDateWithIncreasedHours(int hrsToIncrease) {
        Duration hours = Duration.ofHours(hrsToIncrease);
        Instant instant = Instant.now().plus(hours);

        return new Date(instant.toEpochMilli());
    }


}
