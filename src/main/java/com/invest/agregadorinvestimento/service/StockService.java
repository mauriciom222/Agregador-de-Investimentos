package com.invest.agregadorinvestimento.service;

import org.springframework.stereotype.Service;

import com.invest.agregadorinvestimento.dto.CreateStockDto;
import com.invest.agregadorinvestimento.entity.Stock;
import com.invest.agregadorinvestimento.repository.StockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

    private StockRepository stockRepository;

    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(createStockDto.stockId(), createStockDto.description());
        stockRepository.save(stock);
        
    }

}
