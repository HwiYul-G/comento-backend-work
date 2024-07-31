package com.demo.comentoStatistic.dto.internal;

public record PostDeptStatsDto(
        String yearMonth,
        Long totCnt,
        String requestLog,
        String team
) {

}
