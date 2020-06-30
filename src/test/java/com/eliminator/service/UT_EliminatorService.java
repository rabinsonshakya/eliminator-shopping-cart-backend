package com.eliminator.service;


import com.eliminator.entities.ProductDetail;
import com.eliminator.model.Cart;
import com.eliminator.model.CartContent;
import com.eliminator.model.ProductsInCart;
import com.eliminator.repo.CartRepo;
import com.eliminator.repo.ProductDetailRepo;
import com.mongodb.client.result.UpdateResult;
import javassist.NotFoundException;
import mockit.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UT_EliminatorService {

  @Tested
  EliminatorService underTest;

  @Injectable
  ProductDetailRepo repo;

  @Injectable
  CartRepo mongoCartRepo;

  @Injectable
  MongoTemplate mongoTemplate;

  @Mocked
  UpdateResult updateResult;

  public static final String ID = "123asd";
  public static final String DC = "DC";
  public static final int VAL = 100000;

  @Test
  public void givenCartID_whenGetCartById_then_verifyIdMatchesWithResult() {
    new Expectations() {
      {
        mongoTemplate.findOne((Query) any, Cart.class);
        result = Cart.builder().id(ID).totalAmount(BigDecimal.valueOf(25)).build();
      }
    };
    Cart cart = underTest.getCartById(ID);
    Assert.assertEquals(BigDecimal.valueOf(25), cart.getTotalAmount());
  }

  @Test
  public void givenDataSet_whenGetAllProducts_then_verifyResultSetReturned() {
    List<ProductDetail> results = new ArrayList<>();
    ProductDetail productDetail = new ProductDetail();
    productDetail.setCategory(DC);
    results.add(productDetail);
    new Expectations() {
      {
        repo.findAll();
        result = results;
      }
    };
    List<ProductDetail> allProducts = underTest.getAllProducts();
    Assert.assertEquals(results, allProducts);
  }

  @Test
  public void given_newCart_when_createNewCart_then_verifyIdIsReturned() {
    Cart newCart = Cart.builder().totalAmount(BigDecimal.valueOf(VAL)).build();
    Cart savedCart = Cart.builder().id(ID).totalAmount(BigDecimal.valueOf(VAL)).build();
    new Expectations() {
      {
        mongoCartRepo.save(newCart);
        result = savedCart;
      }
    };
    Cart newCart1 = underTest.createNewCart(newCart);
    Assert.assertEquals(ID, newCart1.getId());
  }

  @Test
  public void given_newCart_when_CartHasIdPopulated_thenDontCreateNewCart() {
    Cart newCart = Cart.builder().id(ID).totalAmount(BigDecimal.valueOf(VAL)).build();
    Cart savedCart = underTest.createNewCart(newCart);
    new Verifications() {
      {
        mongoCartRepo.save(newCart);
        times = 0;
      }
    };
    Assert.assertEquals(newCart, savedCart);
  }

  @Test
  public void given_updateCart_when_cartDoesNotExists_thenThrowNotFoundException() throws Exception {
    ArrayList<ProductsInCart> products = new ArrayList<>();
    products.add(ProductsInCart.builder().build());
    products.add(ProductsInCart.builder().build());
    CartContent cartContent = CartContent.builder().products(products).build();
    new Expectations() {
      {
        mongoTemplate.updateFirst((Query) any, Update.update("products", cartContent.getProducts()), Cart.class);
        result = null;
      }
    };
    Cart cart = null;
    try{
      cart = underTest.updateCart(ID, cartContent);
    }catch (NotFoundException ex){
      assertNull(cart);
    }
  }

  @Test
  public void given_updateCart_when_cartExists_thenUpdateCart() throws Exception {
    Cart newCart = Cart.builder().id(ID).totalAmount(BigDecimal.valueOf(VAL)).build();
    ArrayList<ProductsInCart> products = new ArrayList<>();
    products.add(ProductsInCart.builder().build());
    products.add(ProductsInCart.builder().build());
    CartContent cartContent = CartContent.builder().products(products).build();

    new Expectations() {
      {
        updateResult.wasAcknowledged();
        result = true;

        mongoTemplate.updateFirst((Query) any, Update.update("products", cartContent.getProducts()), Cart.class);
        result = updateResult;

        mongoTemplate.findOne((Query) any, Cart.class);
        result = newCart;
      }
    };
    Cart cart = null;
    try{
      cart = underTest.updateCart(ID, cartContent);
    }catch (NotFoundException ex){
      fail();
    }
    assertEquals(ID, cart.getId());
  }

}