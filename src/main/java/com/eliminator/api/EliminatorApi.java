/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.eliminator.api;

import com.eliminator.model.Body;
import com.eliminator.model.Cart;
import com.eliminator.model.CartContent;
import com.eliminator.model.ProductDetails;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T15:14:29.755577300-05:00[America/Chicago]")

@Api(value = "eliminator", description = "the eliminator API")
public interface EliminatorApi {

  @ApiOperation(value = "GET endpoint for shoppng cart by id", nickname = "getEliminatorCartCartId", notes = "get shoppng cart by id", response = Cart.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Cart.class),
      @ApiResponse(code = 201, message = "Created")})
  @RequestMapping(value = "/eliminator/shoppingcart/{cart_id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Cart> getCartWithId(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId);


  @ApiOperation(value = "get all the products and its details", nickname = "getEliminatorProductDetails", notes = "get all the products and its details", response = ProductDetails.class, responseContainer = "List", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = ProductDetails.class, responseContainer = "List")})
  @RequestMapping(value = "/eliminator/productDetails",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<ProductDetails>> getEliminatorProductDetails();


  @ApiOperation(value = "POST endpoint to create Shopping Cart", nickname = "postEliminatorCart", notes = "use to create Shopping Cart", response = Object.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Object.class)})
  @RequestMapping(value = "/eliminator/shoppingcart",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Cart> postEliminatorCart(@ApiParam(value = "") @Valid @RequestBody Cart body);


  @ApiOperation(value = "POST endpoint to update or modify shopping cart", nickname = "postEliminatorCartCartId", notes = "update or modify shopping cart", response = Cart.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Cart.class)})
  @RequestMapping(value = "/eliminator/shoppingcart/{cart_id}",
      produces = {"application/json"},
      consumes = {"application/json", "application/xml", "multipart/form-data"},
      method = RequestMethod.POST)
  ResponseEntity<Cart> postUpdateEliminatorCartById(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody Cart cart) throws Exception;


  @ApiOperation(value = "PUT endpoint for Checkout", nickname = "postEliminatorCartCartIdCheckout", notes = "Checkout", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK")})
  @RequestMapping(value = "/eliminator/shoppingcart/{cart_id}/checkout",
      consumes = {"application/json", "application/xml"},
      method = RequestMethod.PUT)
  ResponseEntity<Cart> checkoutAndCompleteOrdr(@ApiParam(value = "", required = true) @PathVariable("cart_id") String cartId, @ApiParam(value = "") @Valid @RequestBody Cart body) throws NotFoundException, Exception;

}
