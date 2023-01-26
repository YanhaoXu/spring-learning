package tech.pdai.pdaitechspringboothellodemo.controller;

import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseResult;
import tech.pdai.pdaitechspringboothellodemo.dto.UserDto;

@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

  @Resource
  private RedisTemplate<String, UserDto> redisTemplate;

  @ApiOperation("AddUser")
  @PostMapping("adduser")
  public ResponseResult<UserDto> add(@RequestBody UserDto user) {
    log.info("user:{}", user);
    redisTemplate.opsForValue().set(String.valueOf(user.getUserId()), user);
    return ResponseResult.success(
        redisTemplate.opsForValue().get(String.valueOf(user.getUserId())));
  }

  @ApiOperation("Find")
  @GetMapping("find/{userId}")
  public ResponseResult<UserDto> edit(@PathVariable("userId") String userid) {
    return ResponseResult.success(redisTemplate.opsForValue().get(userid));
  }

}
