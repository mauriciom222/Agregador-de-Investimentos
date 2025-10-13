package com.invest.agregadorinvestimento.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;


import com.invest.agregadorinvestimento.dto.CreateUserDto;
import com.invest.agregadorinvestimento.dto.UpdateUserDTO;
import com.invest.agregadorinvestimento.entity.User;
import com.invest.agregadorinvestimento.repository.UserRepository;

import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class UserService {
    //injetar o repositório de usuários
    //para poder salvar, buscar, atualizar e deletar usuários

    private UserRepository userRepository;
    
/* 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/


    public UUID createUser(CreateUserDto userDto) {
        //converter CreateUserDto para User
        //criar uma instância de User
        
        var entity = new User(null, 
                        userDto.username(), 
                        userDto.email(), 
                        userDto.password(), 
                        Instant.now(), 
                        null);

        //e salvar no banco de dados
        var userSaved = userRepository.save(entity);

        //retornar o id do usuário salvo
        return userSaved.getUserId();

    }
    //buscar usuário por id
    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
         
    }

    //listar todos os usuários
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserbyId(String userId, UpdateUserDTO updateUserDTO) {
        //converter string em UUID
        
        var id = UUID.fromString(userId);

        //buscar o usuário no banco de dados
        var userEntity = userRepository.findById(id);
        //se o usuário existir, atualizar os campos

        //se não existir, não fazer nada
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            if(updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }
            if(updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }

            //dessa forma, o usuário só será atualizado se os campos forem diferentes de null
            //salvar o usuário atualizado no banco de dados
            userRepository.save(user);
        }
            

    }

    //delete
    public void deleteUser(String userId) {
        //converter string em UUID
        var id = UUID.fromString(userId);

        //verificar se o usuário existe
        var exists = userRepository.existsById(id);
        if (exists) {
            userRepository.deleteById(id);
        }
        
    }

}
