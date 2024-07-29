package com.demo.comentoStatistic.dto;

public record PostStatsDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        String requestLog
) {
}
