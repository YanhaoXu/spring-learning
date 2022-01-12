package com.github.xuyh.tacocloud.domain.service;

import com.github.xuyh.tacocloud.domain.model.User;
import com.github.xuyh.tacocloud.domain.repository.JpaUserRepository;
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
