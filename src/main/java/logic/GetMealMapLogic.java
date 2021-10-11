package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.MealDAO;
import entity.MealEntity;

public class GetMealMapLogic {
	public Map<String, List<MealEntity>> execute(int userId) {
		MealDAO dao = new MealDAO();
		List<MealEntity> mealList = new ArrayList<MealEntity>();
		mealList = dao.findALL(userId);
		Collections.sort(mealList);

		Map<String, List<MealEntity>> mealMap = 
				new LinkedHashMap<String, List<MealEntity>>();
		mealMap = mealList.stream()
				.collect(Collectors.groupingBy(MealEntity::getMealDays));
		Map<String, List<MealEntity>> sortedMealMap = sortMapByKey(mealMap);

		return sortedMealMap;
	}
	
	public LinkedHashMap<String, List<MealEntity>> sortMapByKey(Map<String, List<MealEntity>> map) {
	    List<Map.Entry<String, List<MealEntity>>> entries = new LinkedList<>(map.entrySet());
	    Collections.sort(entries, (o1, o2) -> o2.getKey().compareTo(o1.getKey()));

	    LinkedHashMap<String, List<MealEntity>> result = new LinkedHashMap<>();
	    for (Map.Entry<String, List<MealEntity>> entry : entries) {
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	}

}
