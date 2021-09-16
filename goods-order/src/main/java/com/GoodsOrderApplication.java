package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"com.whb"})
@ImportResource("classpath:applicationContext.xml")
public class GoodsOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsOrderApplication.class, args);
    }
}
