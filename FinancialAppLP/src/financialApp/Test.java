/**
 * 
 */
package financialApp;

import java.util.List;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;






/**
 * @author user
 *
 */
public class Test {
	private static final String PERSISTENCE_UNIT_NAME = "FinancialAppJPA";
	private static EntityManagerFactory factory;
	private static EntityManager em = null;
	// 
	public static void main(String [] args) {
		Query q=null;
		// add arrays 
		List<Transaction>transactions=null;
	    List<Category>categories=null;
		// print arrays
	    List<User>dbUsers=null;
	    List<Category>dbCategories=null;
	    
	    factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
		em=factory.createEntityManager();
		/**
		try {
			
			if(em==null) {
				factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
				em=factory.createEntityManager();
			}
		}
		catch (Exception PersistenceException) {
			System.out.print("Database already exists");
		}
		**/
		
		em.getTransaction().begin();
		
		Transaction t1=new Transaction();
		t1.setAmount(50);
		t1.setType("expense");
		t1.setId(0);
		t1.setNotes("bills");
		t1.setDate("00-00-00");
		//tansaction
		Transaction t2=new Transaction();
		t1.setAmount(1500);
		t1.setType("income");
		t1.setId(0);
		t1.setNotes("none");
		t1.setDate("00-00-00");
		
	
		transactions.add(t1);
		transactions.add(t2);
		//category
		CategoryManagement catM=new CategoryManagement(em);
		
		categories.add(catM.addcategory(0, "bills",0,null));
		categories.add(catM.addcategory(0, "sports", 0, null));
		categories.add(catM.addcategory(0, "food", 0, null));
		categories.add(catM.addcategory(0, "transportation", 0, null));
		categories.add(catM.addcategory(0, "leisure", 0, null));
		//user
		UserManagement usM=new UserManagement(em);
		
		usM.addUser(0, "jhon", "123456", categories);
		usM.addUser(0, "Tom","6341343" , categories);
		
		
		em.getTransaction().commit();
		
		dbUsers=usM.findAllUser();
		System.out.println("----------------------------");
		System.out.print("Users table");
		for (User u:dbUsers) {
			System.out.println(u);
		}
		System.out.println("------------------------------");
	    
		
		dbCategories=catM.findAllCategoy();
		System.out.println("----------------------------");
		System.out.println("Category table");
		for (User u:dbUsers) {
			System.out.println(u);
		}
		System.out.println("------------------------------");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
