package dao;

public class AccountDAO {
	
	ProfileDAO profile;
	private String login;
	private String psw;
	private String name;
	
	
	
	public ProfileDAO getProfile() {
		return profile;
	}
	public void setProfile(ProfileDAO profile) {
		this.profile = profile;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
