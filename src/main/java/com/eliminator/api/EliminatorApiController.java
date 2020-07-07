package com.eliminator.api;


import com.eliminator.model.Cart;
import com.eliminator.model.ProductDetails;
import com.eliminator.model.ProductsInCart;
import com.eliminator.service.EliminatorService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

  public ResponseEntity<Cart> getCartWithId(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId) {
    return new ResponseEntity<Cart>(eliminatorService.getCartById(cartId), HttpStatus.OK);
  }

  public ResponseEntity<List<ProductDetails>> getEliminatorProductDetails() {
    ResponseEntity<List<ProductDetails>> responseEntity = new ResponseEntity(eliminatorService.getAllProducts(), HttpStatus.OK);
    return responseEntity;
  }

  public ResponseEntity<Cart> postEliminatorCart(@ApiParam(value = "") @Valid @RequestBody Cart newCart) {
    Cart cart = eliminatorService.createNewCart(newCart);
    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
  }

  public ResponseEntity<Cart> postUpdateEliminatorCartById(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody Cart cart) throws Exception {
    Cart updatedCart = eliminatorService.updateCart(cartId, cart);
    return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
  }

  public ResponseEntity<Cart> checkoutAndCompleteOrdr(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody Cart cart) throws Exception {
    Cart checkedOutcart = eliminatorService.checkOut(cartId, cart);
    return new ResponseEntity<Cart>(HttpStatus.ACCEPTED);
  }

}
