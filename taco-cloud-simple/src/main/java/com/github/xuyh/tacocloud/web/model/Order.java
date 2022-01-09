package com.github.xuyh.tacocloud.web.model;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class Order {
  private Long id;
  private Date placedAt;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Street is required")
  private String street;

  @NotBlank(message = "City is required")
  private String city;

  @NotBlank(message = "State is required")
  private String state;

  @NotBlank(message = "Zip code is required")
  private String zip;

  // @CreditCardNumber(message = "Not a valid credit card number")
  @NotBlank(message = "Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  private List<Taco> tacos = Lists.newArrayList();

  public void addDesign(Taco taco) {
    tacos.add(taco);
  }
}
