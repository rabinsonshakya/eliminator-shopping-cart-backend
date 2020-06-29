package com.eliminator.service;

import com.eliminator.entities.ProductDetail;
import com.eliminator.model.Cart;
import com.eliminator.model.CartContent;
import com.eliminator.repo.CartRepo;
import com.eliminator.repo.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EliminatorService {

  @Autowired
  ProductDetailRepo repo;

  @Autowired
  CartRepo mongoCartRepo;

  @Autowired
  MongoTemplate mongoTemplate;

  public List<ProductDetail> getAllProducts() {
    List<ProductDetail> all = repo.findAll();
    return all;
  }

  public Cart getCartById(String cartId) {
    Query query = new Query(Criteria.where("_id").is(cartId));
    return mongoTemplate.findOne(query, Cart.class);
  }

  public Cart createNewCart(Cart newCart) {
    return mongoCartRepo.save(newCart);
  }

  public Cart saveAndUpdate(String cartId, CartContent cartContent) {
    Query query = new Query(Criteria.where("_id").is(cartId));
    mongoTemplate.updateFirst(query, Update.update("products", cartContent.getProducts()), Cart.class);
    return mongoTemplate.findOne(query, Cart.class);
  }
}
