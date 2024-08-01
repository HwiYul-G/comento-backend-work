package com.demo.comentoStatistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StatisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticApplication.class, args);
	}

}
