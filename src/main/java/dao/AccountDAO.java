package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.AccountEntity;
import entity.RegistrateEntity;
import entity.SignInEntity;
import utility.Utility;

public class AccountDAO {
	
	// SignInEntityのログイン情報を引数に入れてデータベースで照合する
	//  見つかれば登録情報をAccountインスタンスに、なければnullで返す
	public AccountEntity findBySignIn(SignInEntity signInEntity) {
		
		AccountEntity accountEntity=null;
		
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
			Connection connection = dataSource.getConnection();
			
			String sql = 
					"select "
					+ "user_name"
					+ ", pass"
					+ ", mail"
					+ ",user_id"
					+ " from account"
					+ " where user_name = ? and pass = ? ";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			String hashPass = Utility.digest(signInEntity.getPassWord());
			
			pStmt.setString(1, signInEntity.getUserName());
			pStmt.setString(2, hashPass);
			
			ResultSet rs = pStmt.executeQuery();
			
			// 一致したユーザーが存在した場合
			// そのユーザーを表すAccountインスタンスを作成
			if(rs.next()) {
				// 結果票からデータを取得
				String userName = rs.getString("user_name");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				int userId = rs.getInt("user_id");
				accountEntity = new AccountEntity(userName,pass,mail,userId);
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーまたはnullを返す
		return accountEntity;
	}
	
	// RegistUserの新規登録情報を引数に入れて、IDとmailが使われてないかデータベースで照合する
		//  見つかれば既存の登録情報をAccountインスタンスに、なければnullで返す
		public AccountEntity findByIdMail(RegistrateEntity registrateEntity) {
			AccountEntity accountEntity=null;
			
			String sql = 
					"select "
					+ "user_name, "
					+ "pass, "
					+ "mail "
					+ "from account "
					+ "where user_name = ? or mail = ?";
			
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:/comp/env");
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
				Connection connection = dataSource.getConnection();
				
				PreparedStatement pStmt = connection.prepareStatement(sql);
				pStmt.setString(1, registrateEntity.getUserName());
				pStmt.setString(2, registrateEntity.getEmailAddress());
				
				ResultSet rs = pStmt.executeQuery();
				
				// 一致したユーザーが存在した場合
				// そのユーザーを表すAccountインスタンスを作成
				if(rs.next()) {
					// 結果票からデータを取得
					String userName = rs.getString("user_name");
					String pass = rs.getString("pass");
					String mail = rs.getString("mail");
					int userId = rs.getInt("user_id");
					accountEntity = new AccountEntity(userName,pass,mail,userId);
				}
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				return null;
			}
			// 見つかったユーザーまたはnullを返す
			return accountEntity;
		}
		
		public boolean newRegist(RegistrateEntity registrateEntity) {
			
			String sql = 
					"INSERT INTO account("
					+ "user_name, "
					+ "pass, "
					+ "mail)"
					+ " values(?,?,?) ";
			
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:/comp/env");
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/mogmemo");
				Connection connection = dataSource.getConnection();
				PreparedStatement pStmt = connection.prepareStatement(sql);
					
				String hashPass = Utility.digest(registrateEntity.getPassWord());
				
				pStmt.setString(1, registrateEntity.getUserName());
				pStmt.setString(2, hashPass);
				pStmt.setString(3, registrateEntity.getEmailAddress());
				
				int result = pStmt.executeUpdate();
				if(result != 1) {
					return false;
				}
				}catch (SQLException | NamingException e) {
					e.printStackTrace();
					return false;
				}
				
				return true;
			}

}
