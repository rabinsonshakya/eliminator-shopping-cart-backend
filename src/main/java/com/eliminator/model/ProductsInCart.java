package com.eliminator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * ProductsInCart
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T15:14:29.755577300-05:00[America/Chicago]")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ProductsInCart   {
  @JsonProperty("id")
  private BigDecimal id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("quantity")
  private int quantity;

  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("imgUrl")
  private String imgUrl = null;

  public ProductsInCart id(BigDecimal id) {
    this.id = id;
    return this;
  }
}

