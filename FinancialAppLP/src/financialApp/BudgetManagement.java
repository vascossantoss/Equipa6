package financialApp;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		newBudget.setBudgetLimit(salary);
		newBudget.getCategories().clear();
		return newBudget;
	}
	
	public Budget addBudget(Budget b) {
		man.getTransaction().begin();
		man.persist(b);
		man.getTransaction().commit();
		return b;
	}
	
	public Budget updateBudget(Budget budget) {
		Budget b = man.find(Budget.class, budget.getId());
		b.setDescription(budget.getDescription());
		b.setBudgetLimit(budget.getBudgetLimit());
		man.persist(b);
		return b;
	}
	
	public boolean removeBudget(int id ) {
		Budget budRe = findBudget(id);
		if (budRe!=null) {
			man.remove(budRe);
			return true;
		}
		
		return false;
	}
	
	public Budget findBudget(int id) {
		return man.find(Budget.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Budget> findAllBudgets() {
		Query qd = man.createQuery("Select b from Budget b");
		return qd.getResultList();
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
			System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
		}else {
			// Verify if category limit is excedeed
			if(cat.getSumOfTansactions() + t.getAmount() > cat.getLimit()) {
				cat.getTransactions().add(t);
				System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
				System.out.println("Category limit of " + cat.getLimit() +  "€ was excedeed by " + (cat.getSumOfTansactions() - cat.getLimit()) + "€\n");
			}else {
				cat.getTransactions().add(t);   
				System.out.println("Transaction " + t.getNotes() + " added to " + cat.getName() + "!");
				System.out.println("Left: " + (cat.getLimit() - cat.getSumOfTansactions()) + "€\n");
			}
		}
	}
	
	public void generateExtract(Budget budget) {
		Double balance = budget.getBudgetLimit() - budget.getTotalExpense() + budget.getTotalIncome();
		String str = "EXTRACT OF BUDGET " + budget.getDescription() + "\nBalance: " + balance + "€ Total Expense: " + budget.getTotalExpense() + "€ Total Income: "+ budget.getTotalIncome() + "€\n";
		List<Category> cats = budget.getCategories();
		for(Category c : cats) {
			if(c.getLimit() > 0) {
				str += c.getName() + " Planned: " + c.getLimit() + "€ Actual: " + c.getSumOfTansactions() + "€\n";
			}	
		}
		System.out.println(str);
	}
}
