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
	
	public Category addCategory(int id,String name,double limit) {
		Category cat=new Category();
		man.persist(cat);
		cat.setId(id);
		cat.setName(name);
		cat.setLimit(limit);
		cat.getTransactions().clear();
		return cat;
	}
	
	public Category addCategory(Category cat) {
		man.getTransaction().begin();
		man.persist(cat);
		man.getTransaction().commit();
		return cat;
	}
	
	public void updateCategory(int id,String name,double limit) {
		Category cat=findCategory(id);
		cat.setId(id);
		cat.setLimit(limit);
		cat.setName(name);
		
	}
	
	public Category updateCategory(Category cat) {
		Category c = man.find(Category.class, cat.getId());
		c.setName(cat.getName());
		c.setLimit(cat.getLimit());
		man.persist(c);
		return c;
	}
	
	
	public boolean removeCategory(int id ) {
		Category catRe=findCategory(id);
		if (catRe!=null) {
			man.remove(catRe);
			return true;
		}
		
		return false;
	}
	
	
	public Category findCategory(int id) {
		return man.find(Category.class,id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories() {
		Query qd = man.createQuery("Select cat from Category cat");
		return qd.getResultList();
	}
	
	
	
	
	
	

}
