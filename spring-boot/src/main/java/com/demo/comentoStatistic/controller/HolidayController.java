package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.model.Holiday;
import com.demo.comentoStatistic.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayService;

    /* TODO: 새로운 년도에 대해서 업데이트가 필요한 경우
        year: 2024
        아래 주석을 해제 하고, GET request를 보낸다.

        POST Request가 더 적절하지만 브라우저에서 get요청으로 할 수 있도록 편의상 get 이용
    */

    // http://localhost:8031/addHolidaysByYear?year=2022
    @GetMapping(value = "/addHolidaysByYear", produces = "application/json")
    public ResponseEntity<List<Holiday>> insertHolidaysByYear(@RequestParam String year){
        List<Holiday> holidays = holidayService.insertHolidaysByYear(year);
        return ResponseEntity.ok(holidays); // 값을 확인하기 위함
    }

}
