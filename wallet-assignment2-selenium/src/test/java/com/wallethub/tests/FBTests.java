package com.wallethub.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wallethub.BaseTest;
import com.wallethub.pages.FBCreatePostPage;
import com.wallethub.pages.FBHomePage;
import com.wallethub.pages.FBLoginPage;
import com.wallethub.pages.TopNavPage;
import com.wallethub.utils.TestUtils;

public class FBTests extends BaseTest {
	FBLoginPage fbLoginPage;
	FBHomePage fbHomePage;
	FBCreatePostPage fbCreatePostPage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		try {
			String dataFileName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info(
				"\n\n" + "**********************\n starting test:" + m.getName() + "\n**********************" + "\n");
		getDriver().manage().window().maximize();
		getDriver().get(getProps().getProperty("fbURL"));
		fbLoginPage = new FBLoginPage();
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void checkLoginAndPost() {
		fbHomePage = fbLoginPage.loginWithEmailPwd(loginUsers.getJSONObject("facebookUser").getString("username"),
				loginUsers.getJSONObject("facebookUser").getString("password"));
		
		waitForSeconds(20);
		fbCreatePostPage = fbHomePage.clickpostLink();
		waitForSeconds(10);
		fbCreatePostPage.pastePost();
		waitForSeconds(20);
	}
}
