package logic;

import dao.AccountDAO;
import entity.RegistrateEntity;

//ユーザー登録の処理
public class NewRegistLogic {
	public boolean regist(RegistrateEntity registrateEntity) {
		AccountDAO dao = new AccountDAO();
		boolean registResult = dao.newRegist(registrateEntity);
		return registResult;
	}
}
