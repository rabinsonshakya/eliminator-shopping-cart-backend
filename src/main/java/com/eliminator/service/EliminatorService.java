package com.eliminator.service;

import com.eliminator.entities.ProductDetail;
import com.eliminator.model.Cart;
import com.eliminator.repo.CartRepo;
import com.eliminator.repo.ProductDetailRepo;
import com.mongodb.client.result.UpdateResult;
import javassist.NotFoundException;
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
    return repo.findAll();
  }

  public Cart getCartById(String cartId) {
    Query query = new Query(Criteria.where("_id").is(cartId));
    return mongoTemplate.findOne(query, Cart.class);
  }

  public Cart createNewCart(Cart newCart) {
    if (newCart.getId() == null) {
      return mongoCartRepo.save(newCart);
    } else {
      return newCart;
    }
  }

  public Cart updateCart(String cartId, Cart cart) throws Exception {
    Query query = new Query(Criteria.where("_id").is(cartId));
    Cart existingCart = mongoTemplate.findOne(query, Cart.class);
    if (existingCart != null) {
      return mongoTemplate.save(cart);
    } else {
      throw new NotFoundException("Cart Not Found");
    }
  }

  public void deleteCart(String cartId) {
    Query query = new Query(Criteria.where("_id").is(cartId));
    mongoTemplate.remove(query, Cart.class);
  }

  public Cart checkOut(String cartId, Cart cart) throws NotFoundException {
    Query query = new Query(Criteria.where("_id").is(cartId));
    UpdateResult updateResult = mongoTemplate.updateFirst(query, Update.update("orderCompleted", true), Cart.class);
    if (updateResult != null && updateResult.wasAcknowledged()) {
      return mongoTemplate.findOne(query, Cart.class);
    } else {
      throw new NotFoundException("Cart Not Found");
    }
  }

}
