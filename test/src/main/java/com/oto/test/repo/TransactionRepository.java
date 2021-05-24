package com.oto.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oto.test.models.Transaction;
@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	
}
