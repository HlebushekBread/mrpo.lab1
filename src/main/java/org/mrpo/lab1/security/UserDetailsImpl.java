package org.mrpo.lab1.security;

import org.jspecify.annotations.Nullable;
import org.mrpo.lab1.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        //GUEST
        SimpleGrantedAuthority viewProductsAuthority = new SimpleGrantedAuthority("VIEW_PRODUCTS");
        //USER
        SimpleGrantedAuthority viewUserOrdersAuthority = new SimpleGrantedAuthority("VIEW_USER_ORDERS");
        //MANAGER
        SimpleGrantedAuthority searchAndSortProductsAuthority = new SimpleGrantedAuthority("SEARCH_AND_SORT_PRODUCTS");
        SimpleGrantedAuthority viewAllOrdersAuthority = new SimpleGrantedAuthority("VIEW_ALL_ORDERS");
        //ADMIN
        SimpleGrantedAuthority redactProductsAuthority = new SimpleGrantedAuthority("REDACT_PRODUCTS");
        SimpleGrantedAuthority redactOrdersAuthority = new SimpleGrantedAuthority("REDACT_ORDERS");

        switch (String.valueOf(user.getRole().getId())) {
            case "4":
                grantedAuthorities.add(redactOrdersAuthority);
                grantedAuthorities.add(redactProductsAuthority);
            case "3":
                grantedAuthorities.add(viewAllOrdersAuthority);
                grantedAuthorities.add(searchAndSortProductsAuthority);
            case "2":
                grantedAuthorities.add(viewUserOrdersAuthority);
            case "1":
                grantedAuthorities.add(viewProductsAuthority);
        }

        return grantedAuthorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    public User getUser() {
        return this.user;
    }
}
