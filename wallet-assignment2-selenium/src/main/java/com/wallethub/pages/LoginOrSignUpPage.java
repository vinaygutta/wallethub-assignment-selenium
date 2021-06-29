package com.wallethub.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class LoginOrSignUpPage extends BaseTest {

	@FindBy(css = "li:nth-of-type(2) > a[role='tab']")
	private WebElement loginTab;
	
	@FindBy(css = "input#pw1-ipt")
	private WebElement passwordTextbox;
	
	@FindBy(css = "input#em-ipt")
	private WebElement emailTextbox;
	
	@FindBy(css = ".blue.btn.touch-element-cl")
	private WebElement loginButton;
	
	public void clickLoginTab() {
		click(loginTab, "Clicking Login Tab");		
	}
	
	public ReviewPage loginWithEmailPwd(String email, String pwd) {
		sendKeys(emailTextbox, email, "Entering email as - " + email);
		sendKeys(passwordTextbox, pwd, "Entering password");
		click(loginButton, "Clicking Login button");
		return new ReviewPage();
	}

	

}
