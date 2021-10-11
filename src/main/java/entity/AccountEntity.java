package entity;

public class AccountEntity extends AbstractUserEntity{
	
	private int userId;

	public AccountEntity(
			String userName, 
			String passWord, 
			String emailAddress,
			int userId) {
		super(userName, passWord, emailAddress);
		this.setUserId(userId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}