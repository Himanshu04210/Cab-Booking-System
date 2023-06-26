package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
