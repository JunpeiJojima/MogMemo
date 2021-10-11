package entity;

// accountテーブルのレコードを表すEntity
public abstract class AbstractUserEntity {
	private String userName;
	private String passWord;
	private String emailAddress;
	
	public AbstractUserEntity(String userName
						,String passWord
						,String emailAddress
						) {
		this.setUserName(userName);
		this.setPassWord(passWord);
		this.setEmailAddress(emailAddress);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
