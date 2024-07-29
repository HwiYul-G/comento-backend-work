package com.demo.comentoStatistic.dto;

public record LoginStatsDto(
        boolean isSuccess,
        String yearMonth,
        String totCnt,
        double average,
        String requestLog
) {
}
