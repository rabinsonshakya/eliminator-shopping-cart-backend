package com.sirmiss.home.journals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@ComponentScan({"com.sirmiss"})
public class JournalsApplication {
  public static void main(String[] args) {
    SpringApplication.run(JournalsApplication.class, args);
  }
}

