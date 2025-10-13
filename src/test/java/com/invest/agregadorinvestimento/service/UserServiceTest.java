package com.invest.agregadorinvestimento.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.invest.agregadorinvestimento.dto.CreateUserDto;
import com.invest.agregadorinvestimento.repository.UserRepository;
import com.invest.agregadorinvestimento.entity.User;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    //criar os mocks necessários
    @Mock
    private UserRepository userRepository;
    //injetar o serviço que será testado
    @InjectMocks
    private UserService userService;

    @Nested
    class CreateUser {
        @Test
        @DisplayName("Deve criar um usuário com sucesso")
        void shouldCreateUserWithSuccess() {
            //setup
            var user = new User(UUID.randomUUID(), "username", "email@email.com","password",Instant.now() , null);
            
            doReturn(user).when(userRepository).save(any());
            
            var input = new CreateUserDto("username", "email@email.com", "password");
            
            //action
            var output = userService.createUser(input);
            //assert
            assertNotNull(output);
        }
        
    }
}
