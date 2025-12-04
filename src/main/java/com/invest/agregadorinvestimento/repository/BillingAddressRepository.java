package com.invest.agregadorinvestimento.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invest.agregadorinvestimento.entity.BillingAddress;


//responsavel por acessar o banco de dados
//e realizar operações de CRUD (Create, Read, Update, Delete) para a entidade BillingAddress
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {

}
