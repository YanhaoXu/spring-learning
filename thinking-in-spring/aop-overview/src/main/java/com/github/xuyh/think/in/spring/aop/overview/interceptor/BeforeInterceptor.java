package com.github.xuyh.think.in.spring.aop.overview.interceptor;

import java.lang.reflect.Method;

public interface BeforeInterceptor {
  Object before(Object proxy, Method method, Object[] args);
}
