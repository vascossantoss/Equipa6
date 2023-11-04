/**
 * 
 */
package financialApp;

import java.util.List;
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
	
	public static void main(String [] args) {
			
	    List<Userr>Users=null;
	    List<Category>Categories=null;
	    
	    factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
		em=factory.createEntityManager();
		
		try {
			
			if(em==null) {
				factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME );
				em=factory.createEntityManager();
			}
		}
		catch (Exception PersistenceException) {
			System.out.print("Database already exists");
		}
	
		em.getTransaction().begin();
		
		
		Transactionn t1=new Transactionn(0,"00-00-2023",50.00,"none","expense");
		Transactionn t2=new Transactionn(0,"00-00-2023",100.00,"none","expense");
		Transactionn t3=new Transactionn(0,"00-00-2023",500.00,"none","expense");
		Transactionn t4=new Transactionn(0,"00-00-2023",250.00,"none","expense");
	
		
		CategoryManagement catM=new CategoryManagement(em);
		Category cat1=catM.addcategory(0,"sports", 100);
		Category cat2=catM.addcategory(0,"healt", 200);
		Category cat3=catM.addcategory(0,"leisure", 100);
		Category cat4=catM.addcategory(0,"bills", 500);
		Category cat5=catM.addcategory(0,"rent", 800);
	    Category catTest=catM.addcategory(0, "Category test for transactions", 0);
	    
	    
	    catTest.getTransactions().add(t1);
	    catTest.getTransactions().add(t2);
	    catTest.getTransactions().add(t3);
	    catTest.getTransactions().add(t4);
	    
		
		UserManagement usM=new UserManagement(em);
		
		Userr us1=usM.addUser(0, "jhon", "123456");
		Userr us2=usM.addUser(0, "Tom","6341343" );

		
		us1.getCategories().add(cat1);
		us1.getCategories().add(cat2);
		us1.getCategories().add(cat3);
		us1.getCategories().add(cat4);
		us1.getCategories().add(cat5);
		us1.getCategories().add(catTest);
			
		us2.getCategories().add(cat1);
		us2.getCategories().add(cat2);
		us2.getCategories().add(cat3);
		us2.getCategories().add(cat4);
		us2.getCategories().add(cat5);
		us2.getCategories().add(catTest);
		
		em.getTransaction().commit();
		
		Users=usM.findAllUser();
		System.out.println("----------------------------");
		System.out.print("Users table \n ");
		for (Userr us:Users) {
			System.out.println(us);
		}
		System.out.println("----------------------------");
	    
		
		Categories=catM.findAllCategoy();
		System.out.println("----------------------------");
		System.out.println("Category table");
		for (Category c:Categories) {
			System.out.println(c);
		}
		System.out.println("----------------------------");
		System.out.println("FINISHED ");
		
			
		
	}
	

}
