package com.invest.agregadorinvestimento.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invest.agregadorinvestimento.entity.AccountStock;
import com.invest.agregadorinvestimento.entity.AccountStockId;



//responsavel por acessar o banco de dados
//e realizar operações de CRUD (Create, Read, Update, Delete) para a entidade accountstock
@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {

}
