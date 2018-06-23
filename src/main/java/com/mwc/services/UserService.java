package com.mwc.services;

import com.mwc.domain.User;

public interface UserService {
    void save(User user);
    
    void update(User user);

    User findByUsername(String username);
}
