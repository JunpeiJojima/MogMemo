package logic;

import dao.MealDAO;
import entity.MealEntity;

public class PostMealLogic {
	public void execute(MealEntity mealEntity) {
		MealDAO dao = new MealDAO();
		dao.create(mealEntity);
	}
}
