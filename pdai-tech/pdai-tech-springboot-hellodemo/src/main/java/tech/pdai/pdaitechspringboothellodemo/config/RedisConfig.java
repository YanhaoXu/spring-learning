package tech.pdai.pdaitechspringboothellodemo.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RedisConfig {

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss");

  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
//    format.setTimeZone(timeZone);
//    JavaTimeModule javaTimeModule = new JavaTimeModule();
//    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
//    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
//    javaTimeModule.addSerializer(LocalDateTime.class,
//        new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
//    javaTimeModule.addDeserializer(LocalDateTime.class,
//        new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
    ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().failOnEmptyBeans(false)
        .failOnUnknownProperties(false).indentOutput(false).serializationInclusion(Include.NON_NULL)
        .modules(new Jdk8Module(), new JavaTimeModule())
//        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
//            DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
//            SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
//        .timeZone(timeZone)
//        .dateFormat(format)
        .build();

    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(
        objectMapper);
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(genericJackson2JsonRedisSerializer);
    template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }

}
