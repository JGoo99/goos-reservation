package com.goo99.goosreservation.data.dto.owner;

import com.goo99.goosreservation.data.entity.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class OwnerDetails implements UserDetails {

  private final Owner owner;

  public OwnerDetails(Owner owner) {
    this.owner = owner;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return owner.getRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    return owner.getPassword();
  }

  @Override
  public String getUsername() {
    return owner.getOwnerName();
  }

  public String getEmail() {
    return owner.getEmail();
  }

  public Long getId() {
    return owner.getId();
  }

  public Long getStudioId() { return owner.getStudioId(); }

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
