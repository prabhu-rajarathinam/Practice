package naukri;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaukriProfileUpdateTest {
	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeMethod
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incongnito");
		options.addArguments("start-maximized");
		options.addArguments("disable-extensions");
//		options.addArguments("headless");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();

	}

	By usernameTxt = By.id("usernameField");
	By passwordTxt = By.id("passwordField");
	By loginBtn = By.cssSelector("button.waves-effect.waves-light.btn-large.btn-block.btn-bold.blue-btn.textTransform");
	By updateProfileBtn = By.cssSelector(".updateProfile .btn.btn-block.btn-large.white-text");
	By resumeHeadlineQuickLinkBtn = By.xpath("//li[@class='collection-item']/span[text()='Resume Headline']");
	By resumeHeadlineEditBtn = By.xpath("//span[text()='Resume Headline']/following-sibling::span[@class='edit icon']");
	By resumeHeadLineTxt = By.id("resumeHeadlineTxt");
	By resumeHeadlineSaveBtn = By.cssSelector(".row a+.waves-effect.waves-light.btn-large.blue-btn");

	@Test
	public void launchBrowser() {
		open("https://login.naukri.com/nLogin/Login.php");
		waitAndType(usernameTxt, System.getProperty("USERNAME"));
		waitAndType(passwordTxt, System.getProperty("PASSWORD"));
		waitAndClick(loginBtn);
		waitAndClick(updateProfileBtn);
		waitAndClick(resumeHeadlineQuickLinkBtn);
		waitAndClick(resumeHeadlineEditBtn);
		waitAndType(resumeHeadLineTxt,
				"4+ years of experience with Functional and API automation in software testing.");
		waitAndClick(resumeHeadlineSaveBtn);
	}

	private void waitAndClick(By locator) {
		waitForElement(locator).click();
	}

	private void waitAndType(By locator, String sendKeys) {
		waitForElement(locator).clear();
		waitForElement(locator).sendKeys(sendKeys);
	}

	private void open(String website) {
		driver.navigate().to(website);
	}

	private WebElement waitForElement(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
