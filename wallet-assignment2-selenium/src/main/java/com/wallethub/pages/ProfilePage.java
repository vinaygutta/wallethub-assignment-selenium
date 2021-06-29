package com.wallethub.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class ProfilePage extends BaseTest {

	@FindBy(css = ".pr-ct-box.pr-rec > .pr-rec-title")
	private WebElement profPageHeading;
	
	@FindBy(css = ".pr-ct-box.pr-rec a")
	private WebElement recommendCompName;

	public String getPageHeadingText() {
		return getElementText(profPageHeading, "Getting Text in element");
	}
	
	public String getrecommendedNameText() {
		return getElementText(recommendCompName, "Getting recommended company name on profile page");
	}

}
