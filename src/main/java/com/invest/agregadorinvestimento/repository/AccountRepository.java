package com.invest.agregadorinvestimento.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invest.agregadorinvestimento.entity.Account;


//responsavel por acessar o banco de dados
//e realizar operações de CRUD (Create, Read, Update, Delete) para a entidade account
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
