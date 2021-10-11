package entity;

// ログインで入力された情報
public class SignInEntity extends AbstractUserEntity{
	
	public SignInEntity(String userName,String passWord) {
		super(userName, passWord, null);
	}

}
