package com.github.xuyh.tacocloud.domain.entity;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date placedAt;

  @NotBlank(message = "Name is required")
  private String deliveryName;

  @NotBlank(message = "Street is required")
  private String deliveryStreet;

  @NotBlank(message = "City is required")
  private String deliveryCity;

  @NotBlank(message = "State is required")
  private String deliveryState;

  @NotBlank(message = "Zip code is required")
  private String deliveryZip;

  // @CreditCardNumber(message = "Not a valid credit card number")
  @NotBlank(message = "Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  @ManyToMany(targetEntity = Taco.class)
  @JoinTable(
      name = "Taco_Order_Tacos",
      joinColumns = {@JoinColumn(name = "tacoOrder")},
      inverseJoinColumns = {@JoinColumn(name = "taco")})
  private List<Taco> tacos = Lists.newArrayList();

  public void addDesign(Taco taco) {
    tacos.add(taco);
  }

  @PrePersist
  void placeAt() {
    this.placedAt = new Date();
  }

  @ManyToOne private User user;
}
