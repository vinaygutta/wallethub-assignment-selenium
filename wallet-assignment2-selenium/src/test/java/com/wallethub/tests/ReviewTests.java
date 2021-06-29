package com.wallethub.tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;
import com.wallethub.BaseTest;
import com.wallethub.pages.LoginOrSignUpPage;
import com.wallethub.pages.LoginPage;
import com.wallethub.pages.ProfilePage;
import com.wallethub.pages.ReviewPage;
import com.wallethub.pages.ReviewSubmitPage;
import com.wallethub.pages.TopNavPage;
import com.wallethub.utils.TestUtils;

import java.io.InputStream;
import java.lang.reflect.Method;

public class ReviewTests extends BaseTest {
	TopNavPage topNavPage;
	LoginOrSignUpPage loginSuPage;
	ProfilePage profPage;
	ReviewPage rewPage;
	ReviewSubmitPage rwsPage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais = null;
		topNavPage = new TopNavPage();
		rewPage = new ReviewPage();
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
		getDriver().get(getProps().getProperty("siteReviewURL"));
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void checkAllStarsNotLit() {

		Assert.assertTrue(rewPage.checkAllRatingStarsNotLit(), "Some Stars are Lit. Please check");
	}

	@Test
	public void checkHoverOnStarsLit() {

		rewPage.checkAllRatingStarsLitOnHover();
	}

	@Test
	public void submitReviewFourStars() {

		Faker fake = new Faker();

		String fakedesc = fake.lorem().sentence(50);

		rwsPage = rewPage.clickFourStars();
		loginSuPage = rwsPage.submitReviewWithoutLogin(fakedesc);
		loginSuPage.clickLoginTab();
		rewPage = loginSuPage.loginWithEmailPwd(loginUsers.getJSONObject("validUser").getString("username"),
				loginUsers.getJSONObject("validUser").getString("password"));
		waitForSeconds(10);
		Assert.assertEquals(rewPage.returnYourReview(), fakedesc, "Entered review not matched");

		profPage = topNavPage.navUserProf();
		waitForSeconds(10);
		Assert.assertEquals(profPage.getrecommendedNameText(), getStrings().get("recommended_name"),
				"Recommended company not match");
		topNavPage.logOut();
	}
}
