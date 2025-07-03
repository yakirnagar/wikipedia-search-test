package wiki;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WikipediaSearchTest {

	WebDriver driver;

	public WebDriver createChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized"); // או כל ארגומנט שתרצה
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(options);
	}

	@BeforeEach
	public void setUp() {
		driver = createChromeDriver();
	}

	@Test
	public void testSearchInWikipedia() throws InterruptedException {
		System.out.println("Entering Wikipedia...");
		driver.get("https://en.wikipedia.org");
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		
		System.out.println("Inserting \"Selenium\"");
		searchInput.sendKeys("Selenium");
		
		System.out.println("Submitting form");
		searchInput.submit();

		Thread.sleep(3000); // only to hold and see what happens

		
		WebElement heading = driver.findElement(By.id("firstHeading"));
		String headingText = heading.getText();
		System.out.println("Title found: " + headingText);

		
		System.out.println("✅ בודקים אם הכותרת כוללת את המילה 'selenium'");
		assertTrue(headingText.toLowerCase().contains("selenium"));
		
		System.out.println("Test passed");
		Thread.sleep(1000);
	}

	@AfterEach
	public void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}
	}
}
