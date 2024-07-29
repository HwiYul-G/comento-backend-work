package com.demo.comentoStatistic.dto;

public record PostDeptStatsDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        String requestLog,
        String team
) {

}
