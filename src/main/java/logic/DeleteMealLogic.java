package logic;

import dao.MealDAO;

public class DeleteMealLogic {
	
	public void deleteTweet(int id) {
		MealDAO dao = new MealDAO();
		dao.delete(id);
	}

}
