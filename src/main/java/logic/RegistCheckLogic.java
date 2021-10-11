package logic;

import dao.AccountDAO;
import entity.AccountEntity;
import entity.RegistrateEntity;

public class RegistCheckLogic {
	public AccountEntity registCheck(RegistrateEntity registrateEntity) {
		AccountEntity result = null;
		AccountDAO dao = new AccountDAO();
		result = dao.findByIdMail(registrateEntity);
		if(result == null) {
			return result;
		}
		return result;
	}

}
