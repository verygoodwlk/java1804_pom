package com.qf.shop.shop_kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ShopKillApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopKillApplication.class, args);
	}
}
