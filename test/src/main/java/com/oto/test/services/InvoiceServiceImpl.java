package com.oto.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oto.test.exception.InvoiceNotFoundException;
import com.oto.test.models.Invoice;
import com.oto.test.models.Transaction;
import com.oto.test.repo.InvoiceRepository;
import com.oto.test.repo.TransactionRepository;

@Service 
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private TransactionRepository tranRepo; 
	
	@Autowired
	private InvoiceRepository repo; 
	
//	@Autowired
//	private InvoiceUtil util;
	
	public Integer saveInvoice(Invoice inv) {
		ArrayList<Transaction> arr = new ArrayList<>();
		Invoice invoice = new Invoice();
		double totalamt = 0;
		int totalQnt = 0;
		Integer id = null;
		     invoice.setCustomer(inv.getCustomer());
		     invoice.setTransactions(inv.getTransactions());
		     
		     for(int i = 0; i<=inv.getTransactions().size()-1;i++) {
		      totalamt = totalamt+ ((inv.getTransactions().get(i).getPrice())
		    		  *(inv.getTransactions().get(i).getQuantity()));
		      totalQnt = totalQnt + inv.getTransactions().get(i).getQuantity();
		    	 
		     }
		     Date date = java.util.Calendar.getInstance().getTime();  
		     invoice.setDate(date);
		     invoice.setTotal_amount(totalamt);
		     invoice.setTotal_quatity(totalQnt);
		     id = repo.save(invoice).getId();

			return id;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		List<Invoice> list = repo.findAll();
		//JDK 1.8 List Sort (using Comparator)
				list.sort((ob1,ob2)->ob1.getId().intValue()-ob2.getId().intValue());
				//list.sort((ob1,ob2)->ob1.getAmount().compareTo(ob2.getAmount())); //ASC
				//list.sort((ob1,ob2)->ob2.getAmount().compareTo(ob1.getAmount())); // DESC
		return list;
	}
	
	@Override
	public Invoice getOneInvoice(Integer id) throws InvoiceNotFoundException {

		Invoice inv = repo.findById(id)
				.orElseThrow(()->new InvoiceNotFoundException("no data found"));
		return inv;
	}

	@Override
	public Invoice updateInvoice(Invoice inv) {
//		Invoice invoice = new Invoice();
		double totalamt = 0;
		int totalQnt = 0;
//		Integer id = null;
//		     invoice.setCustomer(inv.getCustomer());
//		     invoice.setTransactions(inv.getTransactions());
		     
		     for(int i = 0; i<=inv.getTransactions().size()-1;i++) {
		      totalamt = totalamt+ ((inv.getTransactions().get(i).getPrice())
		    		  *(inv.getTransactions().get(i).getQuantity()));
		      totalQnt = totalQnt + inv.getTransactions().get(i).getQuantity();
		    	 
		     }
		     Date date = java.util.Calendar.getInstance().getTime();  
		     inv.setDate(date);
		     inv.setTotal_amount(totalamt);
		     inv.setTotal_quatity(totalQnt);
		
		return repo.save(inv);
	}

	@Override
	public void deleteInvoice(Integer id) {
		Invoice inv = null;
		try {
			inv = getOneInvoice(id);
		} catch (InvoiceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repo.delete(inv);
	}


	
}
