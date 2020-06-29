package com.eliminator.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Cart
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "shoppingcart")
public class Cart {
  @Id
  private String id;
  @JsonProperty("totalAmount")
  private BigDecimal totalAmount = null;

  @JsonProperty("dateCreated")
  private LocalDateTime dateCreated = null;

  @JsonProperty("products")
  @Valid
  private List<ProductsInCart> products = null;

  @JsonProperty("orderCompleted")
  private Boolean orderCompleted = null;
}

