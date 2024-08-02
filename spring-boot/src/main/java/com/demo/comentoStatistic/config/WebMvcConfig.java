package com.demo.comentoStatistic.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebClient webClient(){
        // 추후 외부 API를 여러 개 사용하는 경우 각 baseURI가 달라질 것을 고려해 우선 webClient만 리턴하게 처리.
        return WebClient.create();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));
        converters.add(new MappingJackson2XmlHttpMessageConverter(new XmlMapper()));
    }
}
