package com.wallethub.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.javafaker.Faker;
import com.wallethub.BaseTest;

public class FBCreatePostPage extends BaseTest {

	@FindBy(css = "div[aria-label$='Eloise?']")
	private WebElement postTextbox;
	
	@FindBy(xpath = "//span[.='Post']")
	private WebElement addPostButton;

	public FBHomePage createPost(String msg) {
		click(postTextbox, "clicking on element");
		sendKeysJS(postTextbox, msg + Keys.ENTER, "Entering message as - " + msg);
		
		return new FBHomePage();
	}
	
	public FBHomePage pastePost() {
		click(postTextbox, "clicking on element");
		Faker fake = new Faker();
		//sendCtrlV(postTextbox, "Pasting msg");
		String msg = fake.harryPotter().character()+ " said to " + fake.hitchhikersGuideToTheGalaxy().character() + " " + fake.lorem().sentence(5);
		sendKeys(postTextbox, msg);
		click(addPostButton,"Clicking add post button");
		
		return new FBHomePage();
	}

}
