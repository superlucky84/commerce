package com.commerce;

import com.commerce.entity.User;
import com.commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jinwoo on 2017. 7. 1..
 */
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    User user = userRepository.findByUsername(username);

    System.out.println(username);
    System.out.println(user);

    return new CustomUserDetails(user);

  }
}
