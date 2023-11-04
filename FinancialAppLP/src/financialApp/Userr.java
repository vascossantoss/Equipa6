/**
 * 
 */
package financialApp;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;




/**
 * @author user
 *
 */
@Entity
public class Userr {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private String username;
	private String password;
	
	public Userr() {
		
	}
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Category>categories=new ArrayList<Category>();
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Category> getCategories(){
		return categories;
	}
	@Override
	public String toString() {
		String ts= "Userr [username=" + username + ", password=" + password + "]";
		for (Category c:categories) {
			ts+=" "+ c + "\n";
		}
		return ts;
	}
	
	
	


	
	
	
	
	
	
	
	
	
	

}
