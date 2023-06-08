package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.IuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IuserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }
}
