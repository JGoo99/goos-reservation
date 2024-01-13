package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserCustomDetails implements UserDetails {

  private final User user;

  public UserCustomDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return user.getRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  public String getEmail() {
    return user.getEmail();
  }

  public Long getId() {
    return user.getId();
  }

  public String getNickname() {
    return user.getNickname();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
