package com.wallethub.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class FBLoginPage extends BaseTest {

	@FindBy(css = "input#email")
	private WebElement emailTextbox;
	
	@FindBy(css = "input#pass")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[@value='1'][contains(.,'Log In')]")
	private WebElement loginButton;

	public FBHomePage loginWithEmailPwd(String email, String pwd) {
		sendKeys(emailTextbox, email, "Entering email as - " + email);
		sendKeys(passwordTextbox, pwd, "Entering password");		
		click(loginButton, "Clicking Login button");
		
		return new FBHomePage();
	}

}
