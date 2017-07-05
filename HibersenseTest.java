import static org.junit.Assert.*;
import java.util.logging.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.concurrent.*;

public class HibersenseTest {

	static WebDriver driver;

	@BeforeClass
        public static void setUpDriver() {

    	// Note that the logging level is a Java standard (thus the
    	// use of a java.util class instead of something specific
    	// to Selenium.  You can modify these levels yourself if,
    	// for example, you would like to see only SEVERE errors.
    	// They can be set to ALL (show all messages) or OFF (no messages)
    	// or to any minimum level from FINEST (most verbose) to SEVERE
    	// (only show the most egregious of errors).
    	// A full lists of levels and descriptions are located at:
    	// https://docs.oracle.com/javase/7/docs/api/java/util/logging/Level.html

    	java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
    	System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    	driver = new HtmlUnitDriver();

        }

	@Before
	public void setUp() throws Exception {
		driver.get("https://hibersense.com/access/login");
	}

	// Given that I am on the main page
	// And I am not logged in
	// When I try to login with an invalid username and invalid password
	// Then I am given a message informing me
	@Test
	public void testValidLogin() {

	        // ENTER YOUR OWN USERNAME/INVALID PASSWORD HERE!
		WebElement var = driver.findElement(By.className("email"));
		WebElement emailIn = var.findElement(By.tagName("input"));
		emailIn.sendKeys("student8@test.com");
		//System.out.println(emailIn.getAttribute("value"));
		WebElement var2 = driver.findElement(By.className("pass"));
		WebElement passIn = var2.findElement(By.tagName("input"));
		passIn.sendKeys("student8");
		//System.out.println(passIn.getAttribute("value"));

		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebElement submitButton = driver.findElement(By.className("btn-login"));
		//System.out.println(submitButton.getText());
		submitButton.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Check that when the login is invalid, remain on same URL
		assertEquals("https://hibersense.com/access/view", driver.getCurrentUrl());

	}

	// Given that I am on the main page
	// And I am not logged in
	// When I try to login with a valid username and valid password
	// Then I am brought to the view page ("/access")
	@Test
	public void testInvalidLogin() {

	        // ENTER YOUR OWN USERNAME/INVALID PASSWORD HERE!
		WebElement var = driver.findElement(By.className("email"));
		var.findElement(By.tagName("input")).sendKeys("studdsfdsent8@test.com");
		WebElement var2 = driver.findElement(By.className("pass"));
		var2.findElement(By.tagName("input")).sendKeys("studsdfsent8");

		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebElement submitButton = driver.findElement(By.className("btn-login"));
		submitButton.click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Check that when the login is invalid, remain on same URL
		assertEquals("https://hibersense.com/access/login", driver.getCurrentUrl());

	}

	// Given that I am on the main page
	// And I am not logged in
	// When I try to login with an invalid username and invalid password
	// Then I am given a message informing me
	@Test
	public void testEditLink() {

		// ENTER YOUR OWN USERNAME/INVALID PASSWORD HERE!
		WebElement var = driver.findElement(By.className("email"));
		var.findElement(By.tagName("input")).sendKeys("student8@test.com");
		WebElement var2 = driver.findElement(By.className("pass"));
		var2.findElement(By.tagName("input")).sendKeys("student8");

		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebElement submitButton = driver.findElement(By.className("btn-login"));
		submitButton.click();
		// Look for the submit button (in the login div) and click
		// to attempt to login

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		List<WebElement> sideMenus = driver.findElements(By.tagName("aside"));

		//System.out.println(driver.getPageSource());

		WebElement mainMenu = null;

		for (WebElement we : sideMenus)
		{
			System.out.println(we.getTagName() + " : " + we.getAttribute("class"));
			if (we.getAttribute("class").equals("main-menu")){
				mainMenu = we;
				break;
			}
		}

		if (mainMenu==null)
			fail("Can\'t find main menu");

		// Check that when the login is invalid, remain on same URL
		assertEquals("https://hibersense.com/access/settings", driver.getCurrentUrl());

	}

	@Test
	public void testTempUnitButtonExists() {

		// ENTER YOUR OWN USERNAME/INVALID PASSWORD HERE!
		WebElement var = driver.findElement(By.className("email"));
		var.findElement(By.tagName("input")).sendKeys("student8@test.com");
		WebElement var2 = driver.findElement(By.className("pass"));
		var2.findElement(By.tagName("input")).sendKeys("student8");

		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebElement submitButton = driver.findElement(By.className("btn-login"));
		submitButton.click();
		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement tempButton = driver.findElement(By.className("celsius"));
		// Check that when the login is invalid, remain on same URL
		
		assertElementExists(mainMenu, By.partialLinkText("F"));
		assertElementExists(mainMenu, By.partialLinkText("C"));		
	}

	private void assertElementExists(SearchContext sc, By b){
		try{
			sc.findElement(b);
		}
		catch(NoSuchElementException nsem)
		{
			fail();
		}
	} 
}
