package com.eliminator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

/**
 * ProductDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T15:14:29.755577300-05:00[America/Chicago]")

public class ProductDetails   {
  @JsonProperty("id")
  private BigDecimal id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("catagory")
  private String category = null;

}

