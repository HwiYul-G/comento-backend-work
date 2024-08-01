package com.demo.comentoStatistic.dto.external.out;

import java.util.Optional;

public record LoginStatsResponseDto(
        boolean isSuccess,
        String yearMonth,
        Long totCnt,
        Optional<Double> average,
        String requestLog
) {
}
