package com.wallethub.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class TopNavPage extends BaseTest {

	@FindBy(css = ".login")
	private WebElement loginLink;
	
	@FindBy(css = ".brgm-button.brgm-list-box.brgm-user > .brgm-list-title")
	private WebElement loggedInUserLink;
	
	@FindBy(css = ".brgm-list.brgm-user-list.ng-enter-element a:nth-of-type(1)")
	private WebElement loggedUserProfileLink;
	
	@FindBy(css = ".brgm-list.brgm-user-list.ng-enter-element > span[role='button']")
	private WebElement logoutUserLink;

	public LoginPage clickLoginLink() {
		click(loginLink, "Clicking Login Link");
		return new LoginPage();
	}
	
	public ProfilePage navUserProf( ) {
		scrollToTop();
		hoverOnElement(loggedInUserLink, "Hovering on logged in user name link");
		click(loggedUserProfileLink, "Clicked user profile link");
	
		return new ProfilePage();
	}
	
	public void logOut( ) {
		scrollToTop();
		hoverOnElement(loggedInUserLink, "Hovering on logged in user name link");
		click(logoutUserLink, "Clicked user profile link");
	
	}

}
