package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer>{

}
