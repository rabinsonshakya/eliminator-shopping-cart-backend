package com.eliminator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Body
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T15:14:29.755577300-05:00[America/Chicago]")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Body   {
  @JsonProperty("paymentinfos")
  @Valid
  private List<Object> paymentinfos = null;
}

