package tech.pdai.pdaitechspringboothellodemo.config.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

@Slf4j
public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull Object handler)
      throws ServletException {

    String newLocale = request.getHeader(getParamName());
    if (newLocale != null) {
      if (checkHttpMethod(request.getMethod())) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
          throw new IllegalStateException(
              "No LocaleResolver found: not in a DispatcherServlet request?");
        }

        try {
          localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
        } catch (IllegalArgumentException ex) {
          if (isIgnoreInvalidLocale()) {
            log.info("Ignoring invalid locale value [{}}]: ", ex.getMessage());
          } else {
            throw ex;
          }
        }
      }
      return true;
    } else {
      return super.preHandle(request, response, handler);
    }
  }

  private boolean checkHttpMethod(String currentMethod) {
    String[] httpMethods = getHttpMethods();
    if (ObjectUtils.isEmpty(httpMethods)) {
      return true;
    }
    for (String httpMethod : httpMethods) {
      if (httpMethod.equalsIgnoreCase(currentMethod)) {
        return true;
      }
    }

    return false;
  }
}
