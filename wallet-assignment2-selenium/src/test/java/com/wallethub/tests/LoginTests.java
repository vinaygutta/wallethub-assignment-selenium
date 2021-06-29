package com.wallethub.tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import com.wallethub.BaseTest;
import com.wallethub.pages.LoginPage;
import com.wallethub.pages.ProfilePage;
import com.wallethub.pages.TopNavPage;
import com.wallethub.utils.TestUtils;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
	TopNavPage topNavPage;
	LoginPage loginPage;
	ProfilePage profPage;
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
		getDriver().get(getProps().getProperty("siteURL"));
		getDriver().manage().window().maximize();
		topNavPage = new TopNavPage();
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void checkLoginLogout() {
		loginPage = topNavPage.clickLoginLink();
		profPage = loginPage.loginWithEmailPwd(loginUsers.getJSONObject("validUser").getString("username"),
				loginUsers.getJSONObject("validUser").getString("password"));
		String act_profHeading = profPage.getPageHeadingText();
		String exp_profHeading = getStrings().get("profile_page_heading");
		

		Assert.assertEquals(act_profHeading, exp_profHeading);
		waitForSeconds(5);
		topNavPage.logOut();
	}
}
