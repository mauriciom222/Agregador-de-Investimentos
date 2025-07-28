package com.invest.agregadorinvestimento.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invest.agregadorinvestimento.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
