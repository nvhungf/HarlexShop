package com.nvhungf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvhungf.entity.Account;

public interface AccountDao extends JpaRepository<Account, String> {

}
