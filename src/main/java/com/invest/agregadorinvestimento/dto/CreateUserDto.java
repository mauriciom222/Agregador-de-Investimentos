package com.invest.agregadorinvestimento.dto;

public record CreateUserDto(String username, String email, String password) {
    // This record can be used to transfer user data
    // It can be extended with validation annotations if needed

}
