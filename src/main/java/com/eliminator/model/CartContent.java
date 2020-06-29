package com.eliminator.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CartContent {
  List<ProductsInCart> products;
}
