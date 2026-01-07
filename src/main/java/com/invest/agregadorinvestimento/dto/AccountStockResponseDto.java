package com.invest.agregadorinvestimento.dto;



public record AccountStockResponseDto(String stockId, 
                                        int quantity,
                                        double precoAtual,
                                        double total, 
                                        double maiorPreco, 
                                        double menorPreco, 
                                        double possivelFechamento, 
                                        double abertura,
                                        double valorizacao
                                        ) {

}
