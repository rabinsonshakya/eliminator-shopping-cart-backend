package com.eliminator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_details")
public class ProductDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private BigDecimal price = null;
  @Column(name = "category")
  private String category = null;
  @Column(name = "img_url")
  private String imgUrl = null;

}
