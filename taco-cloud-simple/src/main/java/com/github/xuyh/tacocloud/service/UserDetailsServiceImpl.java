package com.github.xuyh.tacocloud.service;

import com.github.xuyh.tacocloud.repository.JpaUserRepository;
import com.github.xuyh.tacocloud.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired private JpaUserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user != null) return user;
    throw new UsernameNotFoundException("User '" + username + "' not found");
  }
}
