package com.invest.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.invest.agregadorinvestimento.dto.CreateUserDto;
import com.invest.agregadorinvestimento.entity.User;
import com.invest.agregadorinvestimento.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UUID createUser(CreateUserDto userDto) {
        //DTO -> User(Entity)
        var entity = new User(UUID.randomUUID(), 
                        userDto.username(), 
                        userDto.email(), 
                        userDto.password(), 
                        Instant.now(), 
                        null);

        var userSaved = userRepository.save(entity);
        
        return userSaved.getUserId();

    }
}
