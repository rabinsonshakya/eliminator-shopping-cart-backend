package com.eliminator.service;


import com.eliminator.repo.CartRepo;
import com.eliminator.repo.ProductDetailRepo;
import mockit.Injectable;
import mockit.Tested;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

class UT_EliminatorService {

  @Tested
  EliminatorService underTest;

  @Injectable
  ProductDetailRepo repo;

  @Injectable
  CartRepo mongoCartRepo;

  @Injectable
  MongoTemplate mongoTemplate;

  @Test
  public void test(){
    underTest.getCartById("asdfg");
  }
}