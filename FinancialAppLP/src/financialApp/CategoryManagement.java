/**
 * 
 */
package financialApp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//

/**
 * @author user
 *
 */
public class CategoryManagement {
	protected EntityManager man;
	
	public CategoryManagement(EntityManager man) {
		this.man=man;
		
		
	}
	
	public Category addcategory(int id,String name,double limit) {
		Category cat=new Category();
		man.persist(cat);
		cat.setId(id);
		cat.setName(name);
		cat.setLimit(limit);
		cat.getTransactions().clear();
		//cat.getTransactions().addAll(transactions);
		return cat;
	
		
	}
	
	
	public void updateCategory(int id,String name,double limit) {
		Category cat=searchCategory(id);
		cat.setId(id);
		cat.setLimit(limit);
		cat.setName(name);
		
	}
	
	
	public void removeCategory(int id ) {
		Category catRe=searchCategory(id);
		if (catRe!=null) {
			man.remove(catRe);
		}
		
		
	}
	
	
	public Category searchCategory(int id) {
		return man.find(Category.class,id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategoy() {
		Query qd = man.createQuery("Select cat from Category cat");
		return qd.getResultList();
	}
	
	
	
	
	
	

}
