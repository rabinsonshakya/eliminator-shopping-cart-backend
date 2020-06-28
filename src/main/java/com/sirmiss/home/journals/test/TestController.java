package com.sirmiss.home.journals.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
  @Autowired
  ExampleService exampleService;

  @GetMapping("/product")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<ExampleEntity>> get() {
//    MissKoResponse build = MissKoResponse.builder().id(1).name("Ashna").description("Creve Couer").build();
//    MissKoResponse build1 = MissKoResponse.builder().id(1).name("Rabinson").description("Maryland Heights").build();
//    List<MissKoResponse> missKoResponsesList = new ArrayList<>();
//    missKoResponsesList.add(build1);
    ResponseEntity<List<ExampleEntity>> missKoResponseEntity = new ResponseEntity<>(exampleService.getAllProducts(), HttpStatus.ACCEPTED);
    System.out.println("called");
    return missKoResponseEntity;
  }
}
