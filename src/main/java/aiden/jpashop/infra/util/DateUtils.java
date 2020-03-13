package aiden.jpashop.infra.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

    public static Date nowToDate() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(9)));
    }

    public static Date nowAfterDaysToDate(Long days) {
        return Date.from(LocalDateTime.now().plusDays(days).toInstant(ZoneOffset.ofHours(9)));
    }

    private DateUtils() {
    }
}
