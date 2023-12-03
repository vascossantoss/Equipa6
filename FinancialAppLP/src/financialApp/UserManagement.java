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
	
	public Userr addUser(int id, String username, String password) {
		Userr us = new Userr();
		man.persist(us);
		us.setId(id);
		us.setUsername(username);
		us.setPassword(password);
		us.getBudgets().clear();
		return us;
	}
	
	public Userr addUser(Userr u) {
		man.getTransaction().begin();
		man.persist(u);
		man.getTransaction().commit();
		return u;
	}
	
	public void updateUser(int id, String userName, String password) {	
		Userr us = man.find(Userr.class, id);
		us.setId(id);
		us.setUsername(userName);
		us.setPassword(password);	
	}
	
	public Userr updateUser(Userr user) {
		Userr u = man.find(Userr.class, user.getId());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		man.persist(u);
		return u;
	}
	
	public boolean removeUser(int id) {
		Userr re = findUser(id);
		if(re != null) {
			man.remove(re);
			return true;
		}	
		return false;
	}
	
	public Userr findUser(int id) {
		return man.find(Userr.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Userr> findAllUsers() {
		Query q = man.createQuery("Select us from Userr us");
		return q.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
