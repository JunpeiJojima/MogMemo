package logic;

import dao.AccountDAO;
import entity.AccountEntity;
import entity.SignInEntity;

public class SignInLogic {
	
	public AccountEntity execute(SignInEntity signInEntity) {
		AccountDAO dao = new AccountDAO();
		AccountEntity account = dao.findBySignIn(signInEntity);
		return account;
	}

}
