package tech.pdai.pdaitechspringboothellodemo.config.exception;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.pdai.pdaitechspringboothellodemo.config.exception.ExceptionData.ExceptionDataBuilder;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseResult;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {BindException.class, ValidationException.class,
      MethodArgumentNotValidException.class})
  public ExceptionData handleParameterVerificationException(Exception e) {
    ExceptionDataBuilder exceptionDataBuilder = ExceptionData.builder();

    log.error("Exception:", e);

    if (e instanceof BindException) {
      BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();

      bindingResult.getAllErrors()
          .forEach(a -> exceptionDataBuilder.error(
              ((FieldError) a).getField() + ": " + a.getDefaultMessage()));


    } else if (e instanceof ConstraintViolationException) {
      if (e.getMessage() != null) {
        exceptionDataBuilder.error(e.getMessage());
      }
    } else {
      exceptionDataBuilder.error("invalid parameter");
    }

    return exceptionDataBuilder.build();
  }


  public ResponseResult<Exception> processException(Exception e) {
    log.error(e.getLocalizedMessage(), e);
    return ResponseResult.fail(null,
        tech.pdai.pdaitechspringboothellodemo.config.response.ResponseStatus.HTTP_STATUS_500.getDescription());
  }


  public ResponseResult<BindingResult> processBindingResultException(
      BusinessException businessException) {
    log.error(businessException.getLocalizedMessage(), businessException);
    return ResponseResult.fail(null, businessException.getLocalizedMessage() == null
        ? tech.pdai.pdaitechspringboothellodemo.config.response.ResponseStatus.HTTP_STATUS_500.getDescription()
        : businessException.getLocalizedMessage());
  }
}
