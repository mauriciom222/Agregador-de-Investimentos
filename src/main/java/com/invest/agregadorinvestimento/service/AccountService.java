package com.invest.agregadorinvestimento.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.invest.agregadorinvestimento.client.BrapiClient;
import com.invest.agregadorinvestimento.dto.AccountStockResponseDto;
import com.invest.agregadorinvestimento.dto.AssociateAccountStockDto;
import com.invest.agregadorinvestimento.entity.AccountStock;
import com.invest.agregadorinvestimento.entity.AccountStockId;
import com.invest.agregadorinvestimento.repository.AccountRepository;
import com.invest.agregadorinvestimento.repository.AccountStockRepository;
import com.invest.agregadorinvestimento.repository.StockRepository;

//import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
@Component
public class AccountService {

    @Value("${TOKEN}")
    private String TOKEN;
    private AccountRepository accountRepository;   
    private StockRepository stockRepository;
    private AccountStockRepository accoountStockRepository;
    private BrapiClient brapiClient;




    public AccountService(AccountRepository accountRepository, StockRepository stockRepository,
            AccountStockRepository accoountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accoountStockRepository = accoountStockRepository;
        this.brapiClient = brapiClient;
    }


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
        
       return account.getAccountStocks().stream().map(as -> 
        new AccountStockResponseDto(as.getStock().getStockId(),
                                    as.getQuantity(),
                                    getCurrentPrice(as.getStock().getStockId()), 
                                    getTotal(as.getQuantity(),as.getStock().getStockId()), 
                                    getHighPrice(as.getStock().getStockId()),
                                    getLowPrice(as.getStock().getStockId()),
                                    getPreviousClosePrice(as.getStock().getStockId()),
                                    getPriceOpen(as.getStock().getStockId()),
                                    getChange(as.getStock().getStockId())

                                    )


                                        

                                ) .toList();

                                    
                                
                               
                                                                
         
    }


    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;

    } 
  private double getHighPrice(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketDayHigh();

        return price;

    } 

  private double getLowPrice(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketDayLow();

        return price;

    }
 
    private double getPreviousClosePrice(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketPreviousClose();

        return price;

    }
   
    private double getPriceOpen(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketOpen();

        return price;

    } 
   
        private double getCurrentPrice(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketPrice();

        return price;

    }

    private double getChange(String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketChange();

        return price;

    }

 

}
