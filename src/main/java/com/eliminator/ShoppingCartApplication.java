package com.eliminator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.eliminator"})
public class ShoppingCartApplication {
  public static void main(String[] args) {
    SpringApplication.run(ShoppingCartApplication.class, args);
  }
}

