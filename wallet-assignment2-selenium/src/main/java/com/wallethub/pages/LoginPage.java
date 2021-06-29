package com.wallethub.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class LoginPage extends BaseTest {

	@FindBy(css = "input#email")
	private WebElement emailTextbox;
	
	@FindBy(css = "input#password")
	private WebElement passwordTextbox;
	
	@FindBy(css = "label[role='checkbox']")
	private WebElement rememberemailToggle;
	
	@FindBy(css = ".blue.btn.center.reg-tabs-bt.touch-element-cl")
	private WebElement loginButton;

	public ProfilePage loginWithEmailPwd(String email, String pwd) {
		sendKeys(emailTextbox, email, "Entering email as - " + email);
		sendKeys(passwordTextbox, pwd, "Entering password");
		
		String toggleOn = getAttribute(rememberemailToggle, "aria-checked", "Checking if remember email toggle is ON");
		
		if (toggleOn.equalsIgnoreCase("true")) {
			click(rememberemailToggle, "Turning OFF remember email toggle");
		}
		
		click(loginButton, "Clicking Login button");
		return new ProfilePage();
	}

}
