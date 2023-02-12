package com.github.xuyh;

import com.github.xuyh.pojo.User;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringCloudMqApplicationTests {
  @Resource
  RabbitTemplate template;

  @Test
  void publisher() {
    // 使用convertAndSend方法一步到位,参数基本和之前是一样的
    // 最后一个消息本体可以使Object类型.
    //    template.convertAndSend("amq.direct", "my-yyds", "Hello World!");

    Object res = template.convertSendAndReceive("amq.direct", "my-yyds", new User());
    log.info("收到消费者响应:{}", res);
  }
}
