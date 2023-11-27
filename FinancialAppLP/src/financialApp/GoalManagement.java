package financialApp;

import javax.persistence.EntityManager;

public class GoalManagement {
	protected EntityManager man;
	
	public GoalManagement(EntityManager man) {
		this.man = man;
	}
	
	public Goal addGoal(int id, String description, Double amount) {
		Goal goal = new Goal();
		man.persist(goal);
		goal.setId(id);
		goal.setDescription(description);
		goal.setAmount(amount);
		goal.setSaved(0.00);
		return goal;
	}
	
	public void addToGoal(Goal goal, Double saved) {
		goal.addToSaved(saved);
		System.out.println(saved + "€ added to " + goal.getDescription() + "!");
		System.out.println("Saved: " + goal.getSaved() + "€ of " + goal.getAmount() + "€");
	}
}

