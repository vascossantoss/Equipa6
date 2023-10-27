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
public class UserManagement {
	protected EntityManager  man;
	
	public UserManagement(EntityManager man) {
		this.man=man;
		
	}
	
	
	public User addUser(int id, String username, String passWord,List<Category>categories) {	
		User us =new User();
		man.persist(us);
		us.setId(id);
		us.setUsername(username);;
		us.setPassword(passWord);
		return us;
	}
	
	public void addCateggory(int id,String nome,double limit) {
		Category cat=new Category();
		man.persist(cat);
		cat.setId(id);
		cat.setName(nome);
		cat.setLimit(limit);
		
	}
	
	public void updateUser(int id, String userName, String password) {	
		User us = man.find(User.class, id);
		us.setId(id);
		us.setUsername(userName);
		us.setPassword(password);
		
		
	}
	
	public void removeUser(int id) {
		User re = searchUser(id);
		if(re != null) 
			man.remove(re);
		return;
	}
	
	public User searchUser(int id) {
		return man.find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		Query q = man.createQuery("Select us from User us");
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
