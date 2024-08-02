package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.external.out.LoginDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.LoginStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostDeptStatsResponseDto;
import com.demo.comentoStatistic.dto.external.out.PostStatsResponseDto;
import com.demo.comentoStatistic.service.StatisticService;
import com.demo.comentoStatistic.utils.OrgConstraint;
import com.demo.comentoStatistic.utils.YearMonthConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("${spring.data.rest.base-path}/stats")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping(value = "/logins/{yearMonth}", produces = "application/json")
    public ResponseEntity<LoginStatsResponseDto> getLoginStats(@PathVariable
                                                               @YearMonthConstraint
                                                               String yearMonth,
                                                               @RequestParam(required = false, name = "is-average")
                                                               boolean isAverage,
                                                               @RequestParam(required = false, name = "exclude-holiday")
                                                               boolean excludeHoliday
    ) {
        LoginStatsResponseDto loginStatsResponseDto = statisticService.getLoginStats(yearMonth, isAverage, excludeHoliday);
        return ResponseEntity.ok(loginStatsResponseDto);
    }

    @GetMapping(value = "/logins/{yearMonth}/{org}", produces = "application/json")
    public ResponseEntity<LoginDeptStatsResponseDto> getLoginDeptStats(@PathVariable @YearMonthConstraint String yearMonth, @PathVariable @OrgConstraint String org) {
        LoginDeptStatsResponseDto loginDeptStatsResponseDto = statisticService.getLoginDeptStats(yearMonth, org);
        return ResponseEntity.ok(loginDeptStatsResponseDto);
    }

    @GetMapping(value = "/posts/{yearMonth}", produces = "application/json")
    public ResponseEntity<PostStatsResponseDto> getPostStats(@PathVariable @YearMonthConstraint String yearMonth) {
        PostStatsResponseDto postStatsResponseDto = statisticService.getPostStats(yearMonth);
        return ResponseEntity.ok(postStatsResponseDto);
    }

    @GetMapping(value = "/posts/{yearMonth}/{org}", produces = "application/json")
    public ResponseEntity<PostDeptStatsResponseDto> getPostDeptStats(@PathVariable @YearMonthConstraint String yearMonth, @PathVariable @OrgConstraint String org) {
        PostDeptStatsResponseDto postDeptStatsResponseDto = statisticService.getPostDeptStats(yearMonth, org);
        return ResponseEntity.ok(postDeptStatsResponseDto);
    }

}
