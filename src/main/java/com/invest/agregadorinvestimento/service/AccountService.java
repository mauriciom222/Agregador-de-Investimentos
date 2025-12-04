package com.invest.agregadorinvestimento.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.invest.agregadorinvestimento.dto.AccountStockResponseDto;
import com.invest.agregadorinvestimento.dto.AssociateAccountStockDto;
import com.invest.agregadorinvestimento.entity.AccountStock;
import com.invest.agregadorinvestimento.entity.AccountStockId;
import com.invest.agregadorinvestimento.repository.AccountRepository;
import com.invest.agregadorinvestimento.repository.AccountStockRepository;
import com.invest.agregadorinvestimento.repository.StockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;   
    private StockRepository stockRepository;
    private AccountStockRepository accoountStockRepository;


    public void associateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));

        var stock = stockRepository.findById(dto.stockid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Stock not found"));

        
 
        //DTO para entity
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(id, account, stock, dto.quantity());

        accoountStockRepository.save(entity);
    
   System.out.println("Metodo associateStock chamado com accountId: "+ entity.getId());
    }


    public List<AccountStockResponseDto> listStock(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"));
        
       return account.getAccountStocks().stream().map(as -> new AccountStockResponseDto(as.getStock().getStockId(), as.getQuantity(), 0.0)).toList();
         
    } 

    

}
