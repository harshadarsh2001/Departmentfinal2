package com.harshproject.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harshproject.entity.UserInfo;
import com.harshproject.entity.UserInfoUserDetails;
import com.harshproject.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserInfoUserDetailsService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
