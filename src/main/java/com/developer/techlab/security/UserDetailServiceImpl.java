package com.developer.techlab.security;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.UserLab;
import com.developer.techlab.service.UserLabService;

import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserLabService iUserService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UserLabDTO user = null;
        try {
            user = iUserService.loadUserByEmail(email);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        if (user == null) throw new UsernameNotFoundException("User Not Found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        authorities.add(authority);

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}