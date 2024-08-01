package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.external.out.LoginDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.LoginStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostStatsResponseDto;
import com.demo.comentoStatistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${spring.data.rest.base-path}/stats")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/logins/{yearMonth}")
    public ResponseEntity<LoginStatsResponseDto> getLoginStats(@PathVariable String yearMonth,
                                                               @RequestParam(required = false, name = "is-average")
                                                               boolean isAverage,
                                                               @RequestParam(required = false, name = "exclude-holiday")
                                                               boolean excludeHoliday
    ) {
        LoginStatsResponseDto loginStatsResponseDto = statisticService.getLoginStats(yearMonth, isAverage, excludeHoliday);
        return ResponseEntity.ok(loginStatsResponseDto);
    }

    @GetMapping("/logins/{yearMonth}/{org}")
    public ResponseEntity<LoginDeptStatsResponseDto> getLoginDeptStats(@PathVariable String yearMonth, @PathVariable String org) {
        LoginDeptStatsResponseDto loginDeptStatsResponseDto = statisticService.getLoginDeptStats(yearMonth, org);
        return ResponseEntity.ok(loginDeptStatsResponseDto);
    }

    @GetMapping("/posts/{yearMonth}")
    public ResponseEntity<PostStatsResponseDto> getPostStats(@PathVariable String yearMonth) {
        PostStatsResponseDto postStatsResponseDto = statisticService.getPostStats(yearMonth);
        return ResponseEntity.ok(postStatsResponseDto);
    }

    @GetMapping("/posts/{yearMonth}/{org}")
    public ResponseEntity<PostDeptStatsResponseDto> getPostDeptStats(@PathVariable String yearMonth, @PathVariable String org) {
        PostDeptStatsResponseDto postDeptStatsResponseDto = statisticService.getPostDeptStats(yearMonth, org);
        return ResponseEntity.ok(postDeptStatsResponseDto);
    }

}
