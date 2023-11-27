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
public class UserManagement {
	protected EntityManager  man;
	
	public UserManagement(EntityManager man) {
		this.man=man;
		
	}
	
	
	public Userr addUser(int id, String username, String passWord) {
		Userr us = new Userr();
		man.persist(us);
		us.setId(id);
		us.setUsername(username);
		us.setPassword(passWord);
		us.getBudgets().clear();
		return us;
	}
	
	
	
	public void updateUser(int id, String userName, String password) {	
		Userr us = man.find(Userr.class, id);
		us.setId(id);
		us.setUsername(userName);
		us.setPassword(password);
		
		
	}
	
	public void removeUser(int id) {
		Userr re = searchUser(id);
		if(re != null) 
			man.remove(re);
		return;
	}
	
	public Userr searchUser(int id) {
		return man.find(Userr.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Userr> findAllUser() {
		Query q = man.createQuery("Select us from Userr us");
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
