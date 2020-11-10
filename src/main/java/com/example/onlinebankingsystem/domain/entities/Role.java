package com.example.onlinebankingsystem.domain.entities;

import com.example.onlinebankingsystem.domain.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
