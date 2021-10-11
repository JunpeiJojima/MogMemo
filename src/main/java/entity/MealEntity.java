package entity;

public class MealEntity implements Comparable<MealEntity>{
	
	public static final int moning = 0;
	public static final int lunch = 1;
	public static final int dinner = 2;
	
	private int mealId;
	//朝食、昼食、夕食
	private String mealCategory;
	//朝食…0、昼食…1、夕食…2
	private int mealNo;
	private String cookingName;
	private String cookingMemo;
	private String mealDays;
	private String mealTimes;
	private String imagesPath;
	private int userId;
	
	public MealEntity(int mealId,
						String mealCategory,
						int mealNo,
						String cookingName, 
						String cookingMemo,
						String mealDays,
						String mealTimes,
						String imagesPath,
						int userId
						) {
		this(mealCategory, 
				mealNo, 
				cookingName, 
				cookingMemo, 
				mealDays, 
				mealTimes, 
				imagesPath,
				userId);
		setMealId(mealId);
	}
	
	public MealEntity(String mealCategory,
						int mealNo,
						String cookingName, 
						String cookingMemo,
						String mealDays,
						String mealTimes,
						String imagesPath,
						int userId) {
		setMealCategory(mealCategory);
		setMealNo(mealNo);
		setCookingName(cookingName);
		setCookingMemo(cookingMemo);
		setMealDays(mealDays);
		setMealTimes(mealTimes);
		setImagesPath(imagesPath);
		setUserId(userId);
	}
	
	public int getMealId() {
		return mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	public String getCookingName() {
		return cookingName;
	}
	public void setCookingName(String cookingName) {
		this.cookingName = cookingName;
	}
	public int getMealNo() {

		return mealNo;
	}
	public void setMealNo(int mealNo) {

		this.mealNo = mealNo;
	}
	public String getCookingMemo() {
		return cookingMemo;
	}
	public void setCookingMemo(String cookingMemo) {
		this.cookingMemo = cookingMemo;
	}
	public String getMealCategory() {
		return mealCategory;
	}
	public void setMealCategory(String mealCategory) {
		this.mealCategory = mealCategory;
	}
	public String getMealDays() {
		return mealDays;
	}
	public void setMealDays(String mealDays) {
		this.mealDays = mealDays;
	}
	public String getMealTimes() {
		return mealTimes;
	}
	public void setMealTimes(String mealTimes) {
		this.mealTimes = mealTimes;
	}
	public String getImagesPath() {
		return imagesPath;
	}
	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(MealEntity mealEntity) {
		int check = this.getMealDays().compareTo(mealEntity.getMealDays());
		if(check != 0) {
			return check;
		} else {
			return this.getMealNo() - mealEntity.getMealNo();
		}
	}
}
