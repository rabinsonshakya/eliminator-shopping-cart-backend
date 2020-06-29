package com.eliminator.api;


import com.eliminator.api.EliminatorApi;
import com.eliminator.repo.CartRepo;
import com.eliminator.model.*;
import com.eliminator.service.EliminatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T15:14:29.755577300-05:00[America/Chicago]")

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EliminatorApiController implements EliminatorApi {

  @Autowired
  EliminatorService eliminatorService;

  public ResponseEntity<Cart> getEliminatorCartCartId(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId) {
    return new ResponseEntity<Cart>(eliminatorService.getCartById(cartId),HttpStatus.OK);
  }

  public ResponseEntity<List<ProductDetails>> getEliminatorProductDetails() {
    ResponseEntity<List<ProductDetails>> responseEntity = new ResponseEntity(eliminatorService.getAllProducts(), HttpStatus.ACCEPTED);
    return responseEntity;
  }

  public ResponseEntity<Object> postEliminatorCart(@ApiParam(value = "") @Valid @RequestBody Cart newCart) {
    Cart cart = eliminatorService.createNewCart(newCart);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Id", cart.getId());
    return new ResponseEntity<Object>(httpHeaders, HttpStatus.OK);
  }

  public ResponseEntity<Cart> postEliminatorCartCartId(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody CartContent cartContent) {
    Cart cart = eliminatorService.saveAndUpdate(cartId, cartContent);
    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
  }

  public ResponseEntity<Void> postEliminatorCartCartIdCheckout(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody Body body) {
    return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
  }

}
