package com.sirmiss.home.journals.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleService {

  @Autowired
  ExampleRepo repo;

  public List<ExampleEntity> getAllProducts() {
    List<ExampleEntity> all = repo.findAll();
    return all;
  }
}
