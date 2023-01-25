package tech.pdai.pdaitechspringboothellodemo.controller;

import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.pdaitechspringboothellodemo.config.response.ResponseResult;
import tech.pdai.pdaitechspringboothellodemo.dto.UserDto;

@RestController
@RequestMapping("redis")
public class RedisController {

  @Resource
  private RedisTemplate<String, UserDto> redisTemplate;

  @ApiOperation("AddUser")
  @PostMapping("adduser")
  public ResponseResult<UserDto> add(UserDto user) {
    redisTemplate.opsForValue().set(String.valueOf(user.getUserId()), user);
    return ResponseResult.success(
        redisTemplate.opsForValue().get(String.valueOf(user.getUserId())));
  }


}
