package com.invest.agregadorinvestimento.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invest.agregadorinvestimento.entity.User;

//responsavel por acessar o banco de dados
//e realizar operações de CRUD (Create, Read, Update, Delete) para a entidade User
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
