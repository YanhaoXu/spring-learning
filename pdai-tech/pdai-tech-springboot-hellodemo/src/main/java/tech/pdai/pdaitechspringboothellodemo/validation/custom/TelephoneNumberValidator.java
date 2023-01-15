package tech.pdai.pdaitechspringboothellodemo.validation.custom;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneNumberValidator implements ConstraintValidator<TelephoneNumber, String> {

  private static final String REGEX_TEL = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    try {
      return Pattern.matches(REGEX_TEL, value);
    } catch (Exception e) {
      return false;
    }
  }
}
