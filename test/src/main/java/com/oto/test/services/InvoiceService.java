package com.oto.test.services;

import java.util.List;
import com.oto.test.exception.InvoiceNotFoundException;
import com.oto.test.models.Invoice;

public interface InvoiceService {
	
	/**
	 * Takes Invoice Object as input and returns PK generated
	 */
	Integer saveInvoice(Invoice inv);
	/**
	 * select all rows and provides result as a List<Invoice>
	 */
	List<Invoice> getAllInvoices();
	
	/**
	 * Takes id as input and returns one row as one object
	 * @throws InvoiceNotFoundException 
	 */
	Invoice getOneInvoice(Integer id) throws InvoiceNotFoundException;  //used in RestController
	
	
	/**
	 * Takes existing Invoice data as input and updates values
	 * @return 
	 */
	Invoice updateInvoice(Invoice e);
	
	/**
	 * Takes PK(ID) as input and deletes Invoice Object data
	 */
	void deleteInvoice(Integer id);	
	

}