package com.demo.comentoStatistic.dto.external.out;

public record LoginDeptStatsResponseDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        String requestLog,
        String team
) {
}
