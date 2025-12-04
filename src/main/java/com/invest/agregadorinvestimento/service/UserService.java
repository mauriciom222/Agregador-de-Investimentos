package com.invest.agregadorinvestimento.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.invest.agregadorinvestimento.dto.AccountResponseDTO;
import com.invest.agregadorinvestimento.dto.CreateAccountDto;
import com.invest.agregadorinvestimento.dto.CreateUserDto;
import com.invest.agregadorinvestimento.dto.UpdateUserDTO;
import com.invest.agregadorinvestimento.entity.Account;
import com.invest.agregadorinvestimento.entity.BillingAddress;
import com.invest.agregadorinvestimento.entity.User;
import com.invest.agregadorinvestimento.repository.AccountRepository;
import com.invest.agregadorinvestimento.repository.BillingAddressRepository;
import com.invest.agregadorinvestimento.repository.UserRepository;

import lombok.AllArgsConstructor;





@Service
@AllArgsConstructor
public class UserService {
    //injetar o repositório de usuários
    //para poder salvar, buscar, atualizar e deletar usuários

    private UserRepository userRepository;
    private AccountRepository accoountRepository;
    private BillingAddressRepository billingAddressRepository;
    
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

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
         

        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado"));
        
 

        //DTO para entidade
        var accountEntity = new Account(null, user, null, createAccountDto.description(), new ArrayList<>()); 
        

        System.out.println(accountEntity.getAccountId()+" <------------------------- ID SALVO NO SERVICE");

        var billingAddress = new BillingAddress(accountEntity.getAccountId(), accountEntity, createAccountDto.street(), createAccountDto.number());

        accountEntity.setBillingAddress(billingAddress);
        

        billingAddressRepository.save(billingAddress);
        accoountRepository.save(accountEntity);
        System.out.println(accountEntity.getAccountId()+" <------------------------- ID SALVO NO SERVICE APOS SALVAR NO REPOSITORIO");
        System.out.println(billingAddress.getId()+" <------------------------- ID DO BILLING ADDRESS SALVO NO SERVICE APOS SALVAR NO REPOSITORIO");
        
    }
    public List<AccountResponseDTO> listAccount(String userId) {

        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao encontrado"));

        var accounts = user.getAccounts().stream().map(ac -> new AccountResponseDTO(ac.getAccountId().toString(), ac.getDescription())).toList();
        
        return accounts;
    }

}
