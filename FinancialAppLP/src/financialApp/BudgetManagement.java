package financialApp;

import javax.persistence.EntityManager;
import java.util.List;

public class BudgetManagement {

	protected EntityManager man;
	
	public BudgetManagement(EntityManager man) {
		this.man = man;
	}
	
	public Budget addBudget(int id, String description, Double salary) {
		Budget newBudget = new Budget();
		man.persist(newBudget);
		newBudget.setId(id);
		newBudget.setDescription(description);
		newBudget.setBalance(salary);
		newBudget.setBudgetLimit(salary);
		newBudget.getCategories().clear();
		return newBudget;
	}
	
	public void addCategoryToBudget(Category cat, Budget budget) {
		// Verify if is income category
		if(cat.getLimit() == 0) {
			budget.getCategories().add(cat);
			System.out.println("Category " + cat.getName() + " added to budget " + budget.getDescription() + "!" );
		}else {
			// Verify if budget limit is excedeed
			if(cat.getLimit() > budget.getIncomeLeft() ) {
				System.out.println("Budget limit excedeed! (" + (cat.getLimit() - budget.getIncomeLeft()) + "€ over income)\nTry again with a lower limit.\n" );
			}else { 
				budget.getCategories().add(cat);
				System.out.println("Category " + cat.getName() + " added to budget " + budget.getDescription() + "!" );
				System.out.println(cat.getName() + ": " + cat.getLimit() + "€\nLeft: " + budget.getIncomeLeft() + "€ of " + budget.getBudgetLimit() + "€\n");
			}
		}
		
	}
	
	public void addTransactionToBudget(Budget budget, Category cat, Transactionn t) {
		if(cat.getLimit() == 0) {
			cat.getTransactions().add(t);
			budget.setBalance(budget.getBalance() + t.getAmount());
			System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
		}else {
			// Verify if category limit is excedeed
			if(cat.getSumOfTansactions() + t.getAmount() > cat.getLimit()) {
				cat.getTransactions().add(t);
				budget.setBalance(budget.getBalance() - t.getAmount()); 
				System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
				System.out.println("Category limit of " + cat.getLimit() +  "€ was excedeed by " + (cat.getSumOfTansactions() - cat.getLimit()) + "€\n");
			}else {
				cat.getTransactions().add(t);   
				budget.setBalance(budget.getBalance() - t.getAmount());
				System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
				System.out.println("Left: " + (cat.getLimit() - cat.getSumOfTansactions()) + "€\n");
			}
		}
	}
	
	public void generateExtract(Budget budget) {
		String str = "EXTRACT OF BUDGET " + budget.getDescription() + ":\nBalance: " + budget.getBalance() + "€ Total Expense: " + budget.getTotalExpense() + "€ Total Income: "+ budget.getTotalIncome() + "€\n";
		List<Category> cats = budget.getCategories();
		for(Category c : cats) {
			if(c.getLimit() > 0) {
				str += c.getName() + " Planned: " + c.getLimit() + "€ Actual: " + c.getSumOfTansactions() + "€\n";
			}	
		}
		System.out.println(str);
	}
}
