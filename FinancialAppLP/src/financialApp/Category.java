/**
 * 
 */
package financialApp;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author user
 *
 */
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	
	private int id;
	private String name;
	private double limit;
	
	public Category() {}
	
	public Category(String name, double limit) {
		this. id = 0;
		this.name = name;
		this.limit = limit;
	}
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Transactionn>transactions=new ArrayList<Transactionn>();
	
	public Double getSumOfTansactions() {
		Double sum = 0.00;
		for(Transactionn t : transactions) {
			sum += t.getAmount();
		}
		return sum;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the limit
	 */
	public double getLimit() {
		return limit;
	}


	/**
	 * @param limit the limit to set
	 */
	public void setLimit(double limit) {
		this.limit = limit;
	}
	
	
	public List<Transactionn> getTransactions(){
		return transactions;
	}


	@Override
	public String toString() {
		String s="Category [name=" + name + ", limit=" + limit + "]\n";
		for (Transactionn t:transactions) {
			s+="			"+ t +"\n";
		}
		return s;
	}
	
	
	

	
}
