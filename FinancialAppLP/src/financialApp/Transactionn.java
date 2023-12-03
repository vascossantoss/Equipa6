/**
 * 
 */
package financialApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



/**
 * @author user
 *
 */
@Entity
public class Transactionn {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private String date;
	private double amount;
	private String notes;
	private String type;
	
	public Transactionn() {}
	
	public Transactionn(Double amount, String date, String notes, String type) {
		this.id = 0;
		this.amount = amount;
		this.date = date;
		this.notes = notes;
		this.type = type;
	}
		
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Transactionn [date=" + date + ", amount=" + amount + ", notes=" + notes + ", type=" + type + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
