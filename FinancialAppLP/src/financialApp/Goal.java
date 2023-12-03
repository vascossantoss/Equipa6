package financialApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goal {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private String description;
	private Double amount;
	private Double saved;
	
	public Goal() {}

	public Goal(String description,Double amount,Double saved) {
		this.id = 0;
		this.description=description;
		this.amount=amount;
		this.saved=saved;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the saved
	 */
	public Double getSaved() {
		return saved;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(Double saved) {
		this.saved = saved;
	}
	
	/**
	 * @param saved the saved to set
	 */
	public void addToSaved(Double saved) {
		this.saved += saved;
	}

	@Override
	public String toString() {
		return "Goal [description=" + description + ", amount=" + amount + ", saved=" + saved + ", left=" +  (amount - saved)  + "]";
	}
	
	
}
