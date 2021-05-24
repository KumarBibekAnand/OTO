package com.oto.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oto.test.models.Invoice;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{


	
}
