package com.maybank.wonghengguan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.maybank.wonghengguan.service.DataInitializeService;

@SpringBootApplication
public class WonghengguanApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(WonghengguanApplication.class, args);

		DataInitializeService dataInitializeService = context.getBean(DataInitializeService.class);

		dataInitializeService.insertSampleData();
	}

}
