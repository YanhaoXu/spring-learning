package tech.pdai.pdaitechspringboothellodemo.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PdaitechHandler {

  @InitBinder
  public void handleInitBinder(WebDataBinder dataBinder) {
    dataBinder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
  }
}
