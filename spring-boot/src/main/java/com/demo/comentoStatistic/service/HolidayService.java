package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.HolidayMapper;
import com.demo.comentoStatistic.dto.external.in.HolidayDto;
import com.demo.comentoStatistic.dto.external.in.HolidayResponseDto;
import com.demo.comentoStatistic.exception.ExternalAPIException;
import com.demo.comentoStatistic.model.Holiday;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayService {
    private static final String SPCDE_INFO_URL = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";

    @Value("${holiday.api.service-key}")
    private String serviceKey;

    private final HolidayMapper holidayMapper;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public List<Holiday> insertHolidaysByYear(String year) {
        List<Holiday> holidays = getHolidaysByYear(Integer.parseInt(year));
        holidayMapper.insertHolidays(holidays);
        return holidays;
    }

    private List<Holiday> getHolidaysByYear(int year) {
        String response = fetchHolidayData(year);
        try {
            HolidayResponseDto holidayResponseDto = objectMapper.readValue(response, HolidayResponseDto.class);
            return holidayResponseDto.items()
                    .stream()
                    .map(this::holidayDtoToHolidayConverter)
                    .toList();
        } catch (Exception e) {
            throw new ExternalAPIException("외부 API 데이터 파싱 오류", HttpStatus.INTERNAL_SERVER_ERROR, response);
        }
    }


    private String fetchHolidayData(int year) {
        return webClient.get()
                .uri(getHolidayInYearUri(year))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, res -> res.bodyToMono(String.class)
                        .map(body -> new ExternalAPIException("외부 API 에서 4xx 에러", HttpStatus.BAD_REQUEST, body)))
                .onStatus(HttpStatusCode::is5xxServerError, res -> res.bodyToMono(String.class)
                        .map(body -> new ExternalAPIException("외부 API 에서 5xx 에러", HttpStatus.INTERNAL_SERVER_ERROR, body)))
                .bodyToMono(String.class).block();
    }

//    private URI getHolidayInYearUri(int year) {
//        return UriComponentsBuilder.fromHttpUrl(SPCDE_INFO_URL)
//                .queryParam("serviceKey", serviceKey)
//                .queryParam("_type", "json")
//                .queryParam("solYear", year)
//                .build()
//                .toUri();
//    }

    private URI getHolidayInYearUri(int year) {
        // uriBuilder 통해 생성시 공공API에서 service key가 제대로 적용되지 않는 문제가 있어서 URI로 그대로 string을 넘김
        // 문제가 없다면 UriComponentsBuilder를 사용하는 것이 좋을듯.
        String uriStr = SPCDE_INFO_URL + "?" + "serviceKey=" + serviceKey + "&_type=json" + "solYear=" + year;
        try {
            return new URI(uriStr);
        } catch (URISyntaxException e) {
            log.error("Invalid URI: {}", uriStr);
            throw new RuntimeException("Invalid URI syntax : " + uriStr, e);
        }
    }

    private Holiday holidayDtoToHolidayConverter(HolidayDto holidayDto) {
        return new Holiday(holidayDto.locdate(), holidayDto.dateName());
    }
}
