package com.demo.comentoStatistic.dto;

public record LoginDeptStatsDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        String requestLog,
        String team
) {
}
