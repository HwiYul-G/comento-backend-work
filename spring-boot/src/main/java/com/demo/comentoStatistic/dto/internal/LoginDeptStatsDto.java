package com.demo.comentoStatistic.dto.internal;

public record LoginDeptStatsDto(
        String yearMonth,
        Long totCnt,
        String requestLog,
        String team
) {
}
