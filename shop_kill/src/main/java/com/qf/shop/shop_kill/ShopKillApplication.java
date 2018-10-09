package com.qf.shop.shop_kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class ShopKillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopKillApplication.class, args);
	}
}
