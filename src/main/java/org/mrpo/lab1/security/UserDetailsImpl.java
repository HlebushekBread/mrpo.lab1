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

        SimpleGrantedAuthority viewProductsAuthority = new SimpleGrantedAuthority("VIEW_PRODUCTS");
        SimpleGrantedAuthority viewUserOrdersAuthority = new SimpleGrantedAuthority("VIEW_USER_ORDERS");
        SimpleGrantedAuthority searchAndSortProductsAuthority = new SimpleGrantedAuthority("SEARCH_AND_SORT_PRODUCTS");
        SimpleGrantedAuthority viewAllOrdersAuthority = new SimpleGrantedAuthority("VIEW_ALL_ORDERS");
        SimpleGrantedAuthority editProductsAuthority = new SimpleGrantedAuthority("EDIT_PRODUCTS");
        SimpleGrantedAuthority editOrdersAuthority = new SimpleGrantedAuthority("EDIT_ORDERS");

        switch (String.valueOf(user.getRole().getId())) {
            case "1":
                grantedAuthorities.add(viewProductsAuthority);
                break;
            case "2":
                grantedAuthorities.add(viewProductsAuthority);

                grantedAuthorities.add(viewUserOrdersAuthority);
                break;
            case "3":
                grantedAuthorities.add(viewProductsAuthority);

                grantedAuthorities.add(searchAndSortProductsAuthority);
                grantedAuthorities.add(viewAllOrdersAuthority);
                break;
            case "4":
                grantedAuthorities.add(viewProductsAuthority);

                grantedAuthorities.add(searchAndSortProductsAuthority);
                grantedAuthorities.add(viewAllOrdersAuthority);

                grantedAuthorities.add(editProductsAuthority);
                grantedAuthorities.add(editOrdersAuthority);
                break;
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
