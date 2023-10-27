/**
 * 
 */
package financialApp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author user
 *
 */
public class CategoryManagement {
	protected EntityManager man;
	
	public CategoryManagement(EntityManager man) {
		this.man=man;
		
		
	}
	
	public void addcategory(int id,String name,double limit) {
		Category cat=new Category();
		man.persist(cat);
		cat.setId(id);
		cat.setName(name);
		cat.setLimit(limit);
	
		
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
	
	
	
	
	
	
	
	
	
	
	

}
