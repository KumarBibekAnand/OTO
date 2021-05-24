package com.oto.test.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oto.test.exception.InvoiceNotFoundException;
import com.oto.test.models.Invoice;
import com.oto.test.services.InvoiceService;

import lombok.Value;
/**
 * 
 * @author KUMAR_BIBEK_ANAND
 *
 */

@RestController
@RequestMapping("/")
public class InvoiceController {
	
	@Autowired
	private InvoiceService service; 
	
	/**
	 * 
	 * Case-1 return all saved data
	 */
	@GetMapping("invoices")
	public List<Invoice> getInvoices() {
		
		return service.getAllInvoices();
	}
	
	/**
	 * Case - 2 Store all invice and transaction data
	 * 
	 * Case - 3  For each transaction you have to update transaction line_total based on price and quantity.
	 * Case - 4  For the whole invoice you have to update total_quantity based on sum of quantity in all transactions
	 *		and total_amount based on the sum of line_total in all transactions.
	 * 
	 * @param inv
	 * @return
	 */
	@RequestMapping(value = "invoices", method = RequestMethod.POST, headers = "Accept=application/json")
	public Map<String,Integer> saveInvoice( @Validated @RequestBody Invoice inv){
		
		Map<String,Integer> resp = new HashMap<>();
		try{
			Integer id = service.saveInvoice(inv);
			/**
			 * Case- 5
			 * The response json format in case there is no validation error in data:
		     *		{
			 *		  id: <integer>
			 *		}
			 */
			resp.put("id", id);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("error", 500);
		}
		return resp;
	}

	/**
	 * Case 6 : response will the json representation the invoice with given ID
	 * @param id
	 * @return
	 * @throws InvoiceNotFoundException
	 */
	
	@GetMapping("invoices/{id}")
	public Invoice getInvoices(@PathVariable Integer id) throws InvoiceNotFoundException {
		
		
		return service.getOneInvoice(id);
	}
	
	
	/**
	 * - if there is no id present in the transaction you have to add that new transaction in the invoice.
	 * - if a transaction is absent from the json data you have to delete that transaction from the invoice.
	 * - if a transaction with an id is present in the json data then you have to update that transaction.
     * - After handling the transactions you have to update the invoice total_quantity and total_amount correctly.
	 * 				- The invoice customer can be changed but not invoice date.
	 *				- validate the data in json
	 *
	 * 
	 * 
	 * 
	 * @param inv
	 * @return
	 * @throws InvoiceNotFoundException
	 */

	@RequestMapping(value = "invoices/ID", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Invoice putInvoices(@Validated @RequestBody Invoice inv) throws InvoiceNotFoundException {
		
		
		 return service.updateInvoice(inv);
	}
	
	
	@DeleteMapping("invoices/{id}")
	public void deleteInvoices(@PathVariable Integer id) {
		
		 service.deleteInvoice(id);
	}
}
