package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.MealEntity;

public class MealDAO {

	public List<MealEntity> findALL(int accountId) {

		List<MealEntity> mealList = new ArrayList<>();

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
			Connection connection = dataSource.getConnection();

				String sql = "SELECT "
						+ "meal_id " 
						+ ",meal_category " 
						+ ",meal_no " 
						+ ",cooking_name " 
						+ ",cooking_memo " 
						+ ",days "
						+ ",times "
						+ ",images_path "
						+ ",user_id "
						+ "FROM meal "
						+ "where user_id = ?"
						+ "ORDER BY days desc, meal_no";
				PreparedStatement pStmt = connection.prepareStatement(sql);
				pStmt.setInt(1, accountId);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					int mealId = rs.getInt("meal_id");
					String mealCategory = rs.getString("meal_category");
					int mealNo = rs.getInt("meal_no");
					String cookingName = rs.getString("cooking_name");
					String cookingMemo = rs.getString("cooking_memo");
					String mealDays = rs.getString("days");
					String mealTimes = rs.getString("times");
					String imagesPath = rs.getString("images_path");
					int userId = rs.getInt("user_id");
					MealEntity mealEntity = new MealEntity(mealId, 
													mealCategory, 
													mealNo, 
													cookingName, 
													cookingMemo, 
													mealDays,
													mealTimes,
													imagesPath,
													userId);
					mealList.add(mealEntity);
				}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}
		return mealList;
	}


	public boolean create(MealEntity mealEntity) {

		String sql = "INSERT INTO meal(" 
				+ "meal_category" 
				+ ",meal_no" 
				+ ",cooking_name" 
				+ ",cooking_memo" 
				+ ",days"
				+ ",times" 
				+ ",images_path" 
				+ ",user_id"
				+ ") " 
				+ "values(?,?,?,?,?,?,?,?) ";

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);

			pStmt.setString(1, mealEntity.getMealCategory());
			pStmt.setInt(2, mealEntity.getMealNo());
			pStmt.setString(3, mealEntity.getCookingName());
			pStmt.setString(4, mealEntity.getCookingMemo());
			pStmt.setString(5, mealEntity.getMealDays());
			pStmt.setString(6, mealEntity.getMealTimes());
			pStmt.setString(7, mealEntity.getImagesPath());
			pStmt.setInt(8, mealEntity.getUserId());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean delete(int id) {

		String sql = "DELETE FROM meal WHERE meal_id = ?";

		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
			Connection connection = dataSource.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);

			pStmt.setInt(1, id);

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
