package com.goo99.goosreservation.data.dto;

import com.goo99.goosreservation.data.entity.Driver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class DriverDetails implements UserDetails {

  private final Driver driver;

  public DriverDetails(Driver driver) {
    this.driver = driver;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return driver.getRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    return driver.getPassword();
  }

  @Override
  public String getUsername() {
    return driver.getDriverName();
  }

  public String getEmail() {
    return driver.getEmail();
  }

  public Long getId() {
    return driver.getId();
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
