package logic;

import entity.RegistrateEntity;

public class InputNullCheckLogic {
	public boolean nullCheck(RegistrateEntity registrateEntity) {
		if(registrateEntity.getUserName() == null || registrateEntity.getUserName().length() == 0) {
			return false;
		}else if(registrateEntity.getPassWord() == null || registrateEntity.getPassWord().length() == 0) {
			return false;
		}else if(registrateEntity.getEmailAddress() == null || registrateEntity.getEmailAddress().length() == 0) {
			return false;
		}
		return true;
	}
}
