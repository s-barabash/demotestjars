package com.demo.demotestjars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemotestjarsApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemotestjarsApplication.class, args);
  }

}
