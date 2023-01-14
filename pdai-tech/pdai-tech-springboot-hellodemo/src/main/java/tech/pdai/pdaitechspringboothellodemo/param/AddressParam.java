package tech.pdai.pdaitechspringboothellodemo.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressParam {

  private String city;

  private String zipcode;
}
