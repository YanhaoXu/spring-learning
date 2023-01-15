package tech.pdai.pdaitechspringboothellodemo.config.exception;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class ExceptionData {

  @Singular
  private final List<Object> errors;
}
