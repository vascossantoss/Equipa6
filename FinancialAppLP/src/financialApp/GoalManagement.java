package financialApp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GoalManagement {	
	protected EntityManager man;
	
	public GoalManagement(EntityManager man) {
		this.man = man;
	}
	
	public Goal addGoal(int id, String description, Double amount, Double saved) {
		Goal goal = new Goal();
		man.persist(goal);
		goal.setId(id);
		goal.setDescription(description);
		goal.setAmount(amount);
		goal.setSaved(saved);
		return goal;
	}
	
	public Goal addGoal(Goal goal) {
		man.getTransaction().begin();
		man.persist(goal);
		man.getTransaction().commit();
		return goal;
	}
	
	public void addToGoal(Goal goal, Double saved) {
		goal.addToSaved(saved);
		System.out.println(saved + "€ added to " + goal.getDescription() + "!");
		System.out.println("Saved: " + goal.getSaved() + "€ of " + goal.getAmount() + "€");
	}
	
	public Goal updateGoal(Goal goal) {
		Goal g = man.find(Goal.class, goal.getId());
		g.setDescription(goal.getDescription());
		g.setAmount(goal.getAmount());
		g.setSaved(goal.getSaved());
		man.persist(g);
		return g;
	}
	
	public boolean removeGoal(int id ) {
		Goal goalRe=findGoal(id);
		if (goalRe!=null) {
			man.remove(goalRe);
			return true;
		}
		
		return false;
	}
	
	public Goal findGoal( int id) {
		return man.find(Goal.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Goal> findAllGoals() {
		Query qd = man.createQuery("Select g from Goal g");
		return qd.getResultList();
	}
}

