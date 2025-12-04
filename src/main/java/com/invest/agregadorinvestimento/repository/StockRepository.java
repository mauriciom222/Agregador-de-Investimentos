package com.invest.agregadorinvestimento.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invest.agregadorinvestimento.entity.Stock;


//responsavel por acessar o banco de dados
//e realizar operações de CRUD (Create, Read, Update, Delete) para a entidade stock
@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}
