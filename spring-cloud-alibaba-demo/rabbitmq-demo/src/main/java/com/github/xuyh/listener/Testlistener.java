package com.github.xuyh.listener;

import com.github.xuyh.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Testlistener {
  //  @RabbitListener(queues = "yyds")
  //  public void test(Message message) {
  //    // 定义此方法为队列yyds的监听器,一旦监听到新的消息,就会接受并处理
  //    log.info(new String(message.getBody()));
  //  }

  //  @RabbitListener(queues = "yyds")
  //  public String receiver(Message message) {
  //    log.info("一号消息队列监听器:{}", message.getBody());
  //    return "收到";
  //  }

  // 指定messageConverter为我们刚刚创建的Beam名称
  @RabbitListener(queues = "yyds", messageConverter = "jacksonConverter")
  public String receiver(User user) {
    log.info("user:{}", user);
    return "收到";
  }
}
