package com.github.xuyh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
  @Bean("directExchange")
  public Exchange exchange() {
    return ExchangeBuilder.directExchange("amq.direct").build();
  }

  @Bean("yydsQueue")
  public Queue queue() {
    return QueueBuilder.nonDurable("yyds").build();
  }


  @Bean("binding")
  public Binding binding(@Qualifier("directExchange") Exchange exchange,
      @Qualifier("yydsQueue") Queue queue) {
    return BindingBuilder.bind(queue).to(exchange).with("my-yyds").noargs();
  }

  @Bean("jacksonConverter")
  public Jackson2JsonMessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

}
