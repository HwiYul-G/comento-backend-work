package com.demo.comentoStatistic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    favicon 관련 warning 제거용
    관련 로그: .m.m.a.ExceptionHandlerExceptionResolver :
        Resolved [org.springframework.web.servlet.resource.NoResourceFoundException: No static resource favicon.ico.]
*/

@Configuration
public class FaviconConfig {

    @Controller
    static class FaviconController {
        @GetMapping("favicon.ico")
        @ResponseBody
        void returnNoFavicon() {

        }
    }
}
