package com.commerce;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by jinwoo on 2017. 7. 8..
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

  @Override
  public Long getCurrentAuditor() {
    CustomUserDetails userDetails = getCurrentUser();
    return userDetails == null ? null : userDetails.getId();
  }

  private CustomUserDetails getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    return (CustomUserDetails)authentication.getPrincipal();
  }


}
