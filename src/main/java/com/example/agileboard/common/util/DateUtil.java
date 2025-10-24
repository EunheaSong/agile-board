package com.example.agileboard.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
    
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
