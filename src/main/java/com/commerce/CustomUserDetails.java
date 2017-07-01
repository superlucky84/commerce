package com.commerce;

import com.commerce.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jinwoo on 2017. 7. 1..
 */
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {


  public CustomUserDetails (User user) {
    super(user.getUsername(), user.getPassword(), getAuthorities(user));
  }

  private static Set<GrantedAuthority> getAuthorities(User user) {
    if (CollectionUtils.isEmpty(user.getAuthorities()))
      return null;
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.addAll(user.getAuthorities());
    return grantedAuthorities;
  }

}
