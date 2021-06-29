package com.wallethub.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.wallethub.BaseTest;

public class ReviewSubmitPage extends BaseTest {

	@FindBy(css = "#reviews-section > modal-dialog > div > div > write-review > sub-navigation > div > div.sbn-action.semi-bold-font.btn.fixed-w-c.tall")
	private WebElement submitButton;

	@FindBy(css = ".wrev-drp .dropdown-placeholder")
	private WebElement selectDrpDwn;

	@FindBy(css = ".wrev-drp .ng-enter-element .dropdown-item:nth-of-type(2)")
	private WebElement selectItmTwo;

	@FindBy(css = ".textarea.validate.wrev-user-input")
	private WebElement commentTxtArea;

	public LoginOrSignUpPage submitReviewWithoutLogin(String fakedesc) {

		sendKeys(commentTxtArea, fakedesc, "Entering review");
		click(selectDrpDwn, "Clicking on select dropdown");
		click(selectItmTwo, "choosing item");
		waitForSeconds(5);
		click(submitButton, "clicking submit button");
		return new LoginOrSignUpPage();
	}

}
