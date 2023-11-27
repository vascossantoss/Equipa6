/**
 * @author Equipa 6
 *
 */
package financialApp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Test {
	private static final String PERSISTENCE_UNIT_NAME = "FinancialAppJPA";
	private static EntityManagerFactory factory;
	private static EntityManager em = null;
	
	public static void main(String [] args) {
			
	    List<Userr>users=null;
//	    List<Category>Categories=null;
	    
	    factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
		em=factory.createEntityManager();
		
//		try {
//			
//			if(em==null) {
//				factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
//				em=factory.createEntityManager();
//			}
//		}
//		catch (Exception PersistenceException) {
//			System.out.print("Database already exists");
//		}
	
		em.getTransaction().begin();
		UserManagement usM=new UserManagement(em);
		BudgetManagement budgetMan = new BudgetManagement(em);
		TransactionManagement tMan = new TransactionManagement(em);
		GoalManagement gMan = new GoalManagement(em);
		// Create user
		Userr us1=usM.addUser(0, "Jhon", "123456");
		
		// Create budget 
		Budget novoBudget = budgetMan.addBudget(0, "myHousehold", 1200.00);
		
		// Create categories
		CategoryManagement catM=new CategoryManagement(em);
		Category cat1 = catM.addcategory(0,"Rent", 700);
		Category cat2 = catM.addcategory(0,"Internet", 50);
		Category cat3 = catM.addcategory(0,"Groceries", 300);
		Category cat4 = catM.addcategory(0,"Gym", 100);
		Category cat5 = catM.addcategory(0,"Health Insurance", 250);
		Category cat6 = catM.addcategory(0,"Bonus", 0);
		
		// Add categories to budget
		System.out.println("-----------------------------------------------------");
		System.out.println("ADDING CATEGORIES TO BUDGET");
		System.out.println("-----------------------------------------------------");
		budgetMan.addCategoryToBudget(cat1, novoBudget);
		budgetMan.addCategoryToBudget(cat2, novoBudget);
		budgetMan.addCategoryToBudget(cat3, novoBudget);
		budgetMan.addCategoryToBudget(cat4, novoBudget);
		budgetMan.addCategoryToBudget(cat5, novoBudget);
		budgetMan.addCategoryToBudget(cat6, novoBudget);
		
		// Attribute budget to user
		us1.getBudgets().add(novoBudget);
		
		// Create transactions
		Transactionn t1 = tMan.addTransaction(0, 100.00, "00-00-2023", "none", "expense");
		Transactionn t2 = tMan.addTransaction(0, 100.00, "00-00-2023", "none", "expense");
		Transactionn t3 = tMan.addTransaction(0, 350.00, "00-00-2023", "1", "expense");
		Transactionn t4 = tMan.addTransaction(0, 150.00, "00-00-2023", "2", "expense");
		Transactionn t5 = tMan.addTransaction(0, 50.00, "00-00-2023", "October Bonus", "income");
		//Add transactions to budget
		System.out.println("-----------------------------------------------------");
		System.out.println("ADDING TRANSACTIONS TO CATEGORIES");
		System.out.println("-----------------------------------------------------");
		budgetMan.addTransactionToBudget(novoBudget, cat3, t4);
		budgetMan.addTransactionToBudget(novoBudget, cat3, t3);
		budgetMan.addTransactionToBudget(novoBudget, cat6, t5);
		// Create goal
		Goal goal1 = gMan.addGoal(0, "Holidays", 2000.00);
		
		System.out.println("-----------------------------------------------------");
		System.out.println("ADDING GOAL");
		System.out.println("-----------------------------------------------------");
		//Add to goal 
		gMan.addToGoal(goal1, 500.00);
		// Add goal to user
		us1.getGoals().add(goal1);
		// Generate extract
		System.out.println("-----------------------------------------------------");
		System.out.println("GENERATING EXTRACT");
		System.out.println("-----------------------------------------------------");
		budgetMan.generateExtract(novoBudget);
		
		System.out.println("-----------------------------------------------------");
		System.out.println("FINISHED");
		System.out.println("-----------------------------------------------------");
		
		em.getTransaction().commit();
		
		users = usM.findAllUser();
		System.out.println("----------------------------");
		System.out.print("USER TABLE\n");
		for (Userr us: users) {
			System.out.println(us);
		}
		System.out.println("----------------------------");
	   
		System.out.println("----------------------------");		
	}// fim main
}// fim Test
