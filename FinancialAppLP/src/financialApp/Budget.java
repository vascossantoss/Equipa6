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

@Entity
public class Budget {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private String description;
	private Double balance;
	private Double budgetLimit;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Category>categories=new ArrayList<Category>();
	
	public Budget() {
		
	}
	
	/**
	 * 
	 * @param budget
	 * @return dif 
	 */
	public Double getIncomeLeft() {
		Double sum = 0.00;		
		for(Category cat : categories ) {
			sum += cat.getLimit();
		}
		Double dif = budgetLimit - sum;
		return dif;
	}
	
	public Double getTotalExpense() {
		Double sum = 0.00;
		for(Category c : categories) {
			if(c.getLimit() > 0) {
				sum += c.getSumOfTansactions();
			}	
		}
		return sum;
	}
	
	public Double getTotalIncome() {
		Double sum = 0.00;
		for(Category c : categories) {
			if(c.getLimit() == 0) {
				sum += c.getSumOfTansactions();
			}	
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startBalance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param startBalance the startBalance to set
	 */
	public void setBalance(Double startBalance) {
		this.balance = startBalance;
	}

	/**
	 * @return the budgetLimit
	 */
	public Double getBudgetLimit() {
		return budgetLimit;
	}

	/**
	 * @param budgetLimit the budgetLimit to set
	 */
	public void setBudgetLimit(Double budgetLimit) {
		this.budgetLimit = budgetLimit;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public String toString() {
		String s = "Budget [description=" + description + ", balance=" + balance + ", budgetLimit=" + budgetLimit + "]\n";
		for (Category c : categories) {
			s+="		"+ c + "\n";
		}
		return s;
	}

	
	
	
}
