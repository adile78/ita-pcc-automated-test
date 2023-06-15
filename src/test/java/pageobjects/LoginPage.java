package pageobjects;

public interface LoginPage {

	public void setUsername(String username);

	public void setPassword(String password);

	public void clickBtnLogin();

	public void loginAction(String username, String password);

}
