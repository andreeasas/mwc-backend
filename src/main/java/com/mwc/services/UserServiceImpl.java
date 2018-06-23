package com.mwc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mwc.domain.User;
import com.mwc.repositories.RoleRepository;
import com.mwc.repositories.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));    
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
    
    @Override
    public void update(User user) {
    	userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
