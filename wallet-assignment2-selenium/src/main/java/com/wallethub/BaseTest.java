package com.wallethub;

import com.aventstack.extentreports.Status;
import com.wallethub.reports.ExtentReport;
import com.wallethub.utils.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal<String> browser = new ThreadLocal<String>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	private static ThreadLocal<String> dateStr = new ThreadLocal<String>();

	TestUtils utils = new TestUtils();

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driver2) {
		driver.set(driver2);
	}

	public String getDateStr() {
		return dateStr.get();
	}

	public void setDateStr(String string) {
		dateStr.set(string);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}

	public String getBrowser() {
		return browser.get();
	}

	public void setBrowser(String browser2) {
		browser.set(browser2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public BaseTest() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(getDriver(), 10);
		PageFactory.initElements(factory, this);
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	// stop video capturing and create *.mp4 file
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {

	}

	@BeforeSuite
	public void beforeSuite() throws Exception {

	}

//	@AfterSuite
//	public void afterSuite() {

//	}

	@Parameters({ "browser" })
	@BeforeTest
	public synchronized void beforeTest(String browser) throws Exception {
		setDateTime(utils.dateTime());
		setDateStr(utils.dateStr());
		setBrowser(browser);
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		WebDriver driver = null;

		String strFile = System.getProperty("outputFolder") + File.separator + "logs" + File.separator + browser;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);

		try {
			props = new Properties();
			String propFileName = "config.properties";
			String xmlFileName = "strings/strings.xml";

			utils.log().info("load " + propFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			setProps(props);

			utils.log().info("load " + xmlFileName);
			stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			setStrings(utils.parseStringXML(stringsis));

			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "msedge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				
				break;
			default:
				throw new Exception("Invalid platform! - " + browser);
			}
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (stringsis != null) {
				stringsis.close();
			}
		}
	}

	public synchronized void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public synchronized void clear(WebElement e) {
		waitForVisibility(e);
		e.clear();
	}

	public synchronized void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}

	public synchronized void click(WebElement e, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.click();
	}

	public synchronized void sendKeys(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public synchronized void sendKeys(WebElement e, String txt, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.sendKeys(txt);
	}
	
	public synchronized void sendCtrlV(WebElement e, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.sendKeys(Keys.CONTROL+ "v");
	}
	
	public synchronized void sendKeysJS(WebElement e, String txt, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		String js = "arguments[0].setAttribute('value','"+txt+"')";
		((JavascriptExecutor) getDriver()).executeScript(js, e);
	}

	public synchronized void waitForSeconds(long timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized int getTotalElementsinList(List<WebElement> e, String msg) {
		utils.log().info(msg + ": " + e.size());
		ExtentReport.getTest().log(Status.INFO, msg + ": " + e.size());

		return e.size();

	}

	public synchronized String getAttribute(WebElement e, String attribute, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		return e.getAttribute(attribute);
	}

	public synchronized String getElementText(WebElement e, String msg) {
		waitForVisibility(e);
		utils.log().info(msg + " " + e.getText());
		ExtentReport.getTest().log(Status.INFO, msg + " " + e.getText());
		return e.getText();
	}

	public synchronized void scrollToElement(WebElement element, String msg) {
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public synchronized void scrollToTop() {
		
		utils.log().info("Scrolling to top of page");
		ExtentReport.getTest().log(Status.INFO, "Scrolling to top of page");

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollTo(0,0)");
	}

	public synchronized void hoverOnElement(WebElement element, String msg) {
		waitForVisibility(element);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);

		Actions action = new Actions(getDriver());
		action.moveToElement(element).build().perform();
	}

	public synchronized boolean checkStarLit(List<WebElement> element, String msg) {
		waitForVisibility(element.get(0));
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);

		boolean isLit = false;
		String expectedD = getStrings().get("d");
		
		String expectedFill = getStrings().get("fill");
		

		for (WebElement e : element) {
			String d = getAttribute(e, "d", "Getting attribute d for path element");
			utils.log().info(d);
			String fill = getAttribute(e, "fill", "Getting fill attribute of the path element");
			utils.log().info(fill);
			if (d.equalsIgnoreCase(expectedD) && fill.equalsIgnoreCase(expectedFill)) {
				isLit = true;
				utils.log().info("Star is Lit");
				ExtentReport.getTest().log(Status.INFO, "Star is Lit");
				break;
			}
		}

		if (!isLit) {
			utils.log().info("Star is not Lit");
			ExtentReport.getTest().log(Status.INFO, "Star is not Lit");
		}
		return isLit;
	}
	

	@AfterTest
	public synchronized void afterTest() {
		getDriver().quit();
	}
}
