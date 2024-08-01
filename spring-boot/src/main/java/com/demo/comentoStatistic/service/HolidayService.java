package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.HolidayMapper;
import com.demo.comentoStatistic.dto.external.in.HolidayDto;
import com.demo.comentoStatistic.dto.external.in.HolidayResponseDto;
import com.demo.comentoStatistic.model.Holiday;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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

    private final RestTemplate restTemplate;
    private final HolidayMapper holidayMapper;

    public List<Holiday> insertHolidaysByYear(String year) {
        try {
            List<Holiday> holidays = getHolidaysByYear(Integer.parseInt(year));
            holidayMapper.insertHolidays(holidays);
            return holidays;
        } catch (Exception e) {
            log.error("Error inserting holidays for year {}: {}", year, e.getMessage());
            throw new RuntimeException("Failed to insert holidays", e);
        }
    }

    private List<Holiday> getHolidaysByYear(int year) {
        try {
            URI uri = getHolidayInYearUri(year);
            HolidayResponseDto response = restTemplate.getForObject(uri, HolidayResponseDto.class);
            if (response == null || response.items() == null) {
                // TODO: 공공api 서버에서 올바른 요청을 해도 HTTP ROUTING ERROR가 발생하는 문제가 있음 해결 방법 모색 중
                log.error("response나 response.items()에 null이 있음");
                log.error(response.toString());
                throw new RuntimeException("Null response received from holiday API");
            }
            return response.items().stream().map(this::holidayDtoToHolidayConverter).toList();
        } catch (RestClientException e) {
            log.error("공휴일 API 호출 문제: {}", e.getMessage());
            throw new RuntimeException("holidays fetch 오류", e);
        }
    }

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
