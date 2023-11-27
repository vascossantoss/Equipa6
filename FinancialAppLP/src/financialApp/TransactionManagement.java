package financialApp;

import javax.persistence.EntityManager;

public class TransactionManagement {

protected EntityManager man;
	
	public TransactionManagement(EntityManager man) {
		this.man = man;
	}
	
	public Transactionn addTransaction(int id, Double amount, String date, String notes, String type) {
		Transactionn t = new Transactionn();
		man.persist(t);
		t.setId(id);
		t.setAmount(amount);
		t.setNotes(notes);
		t.setDate(date);
		t.setType(type);
		return t;
	
		
	}
}
