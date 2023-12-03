package financialApp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public Transactionn addTransaction(Transactionn t) {
		man.getTransaction().begin();
		man.persist(t);
		man.getTransaction().commit();
		return t;
	}
	
	public Transactionn updateTransaction(int id, Double amount, String date, String notes, String type) {
		Transactionn t = man.find(Transactionn.class, id);
		t.setId(id);
		t.setAmount(amount);
		t.setNotes(notes);
		t.setDate(date);
		t.setType(type);
		man.persist(t);
		return t;
	}
	
	public Transactionn updateTransaction(Transactionn tr) {
		Transactionn t = man.find(Transactionn.class, tr.getId());
		t.setAmount(tr.getAmount());
		t.setNotes(tr.getNotes());
		t.setDate(tr.getDate());
		t.setType(tr.getType());
		man.persist(t);
		return t;
	}
	
	public Transactionn findTransaction( int id) {
		return man.find(Transactionn.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Transactionn> findAllTransactions() {
		Query qd = man.createQuery("Select tr from Transactionn tr");
		return qd.getResultList();
	}
}
