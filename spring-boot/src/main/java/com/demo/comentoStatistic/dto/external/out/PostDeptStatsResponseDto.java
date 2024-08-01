package com.demo.comentoStatistic.dto.external.out;

public record PostDeptStatsResponseDto(
    boolean isSuccess,
    String yearMonth,
    Long totCnt,
    String requestLog,
    String team
) {
}
