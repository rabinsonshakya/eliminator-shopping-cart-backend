package com.eliminator.api.integrationTests;

import com.eliminator.ShoppingCartApplication;
import com.eliminator.model.Cart;
import com.eliminator.model.ProductsInCart;
import com.eliminator.service.EliminatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ShoppingCartApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class IT_EliminatorApiController {

  public static final String AUTHORIZATION = "authorization";
  public static final String TOKEN = "Token";
  public static final String CART_WITH_BATMOBILE = "5ef9d615faa37f49810d913b";
  public static final String BAT_WINGS = "BatWings";
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  EliminatorService eliminatorService;


  @Test
  void given_newCart_when_postToCreateCart_then_verifyIdInResponseHeader() throws Exception {
    Cart cart = buildCart(null);
    String id = null;
    try {
      MvcResult mvcResult = mockMvc.perform(post("/eliminator/shoppingcart")
          .contentType("application/json")
          .content(objectMapper.writeValueAsString(cart))
          .header(AUTHORIZATION, TOKEN)).andReturn();
      id = mvcResult.getResponse().getHeader("Id");
      assertNotNull(id);
    } catch (Exception e) {
      //remove the created test data
      eliminatorService.deleteCart(id);
    }
    //remove the created test data
    eliminatorService.deleteCart(id);
  }

  @Test
  void given_ExistingCart_when_postToUpdateCart_then_verifyCartUpdated() throws Exception {
    Cart cart = buildCart(CART_WITH_BATMOBILE);
    String id = null;
    MvcResult mvcResult = mockMvc.perform(post("/eliminator/shoppingcart/" + CART_WITH_BATMOBILE)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(cart))
        .header(AUTHORIZATION, TOKEN)).andReturn();
    Cart updatedCart = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Cart.class);
    assertEquals(BAT_WINGS, updatedCart.getProducts().get(1).getName());
  }

  @Test
  void given_ExistingCartInMongoDb_when_getCartWithId_then_verifyIdMatches() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/eliminator/shoppingcart/" + CART_WITH_BATMOBILE)
        .header(AUTHORIZATION, TOKEN)).andReturn();
    Cart cart = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Cart.class);
    assertEquals(CART_WITH_BATMOBILE, cart.getId());
  }

  @Test
  void given_authorizationTokenPresent_when_getProductDetailsInvoked_then_checkStatusOK() throws Exception {
    mockMvc.perform(get("/eliminator/productDetails")
        .header(AUTHORIZATION, TOKEN))
        .andExpect(status().isOk());
  }

  @Test
  void given_authorizationTokenNotPresent_when_getProductDetailsInvoked_then_checkStatusUnauthorized() throws Exception {
    mockMvc.perform(get("/eliminator/productDetails"))
        .andExpect(status().isUnauthorized());
  }

  private Cart buildCart(String cartId) {
    ArrayList<ProductsInCart> products = new ArrayList<>();
    products.add(ProductsInCart.builder().id(BigDecimal.valueOf(1)).name("Batarang").price(BigDecimal.valueOf(654897)).quantity(5).build());
    products.add(ProductsInCart.builder().id(BigDecimal.valueOf(2)).name(BAT_WINGS).price(BigDecimal.valueOf(8975643)).quantity(2).build());
    return Cart.builder().id(cartId).totalAmount(BigDecimal.valueOf(1000000)).dateCreated(LocalDateTime.now()).orderCompleted(Boolean.FALSE).products(products).build();
  }

}