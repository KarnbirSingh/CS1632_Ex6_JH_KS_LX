import static org.junit.Assert.*;
import java.util.logging.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;

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
	public void testBadPasswordResetLink() {

	        // ENTER YOUR OWN USERNAME/INVALID PASSWORD HERE!
		WebElement var = driver.findElement(By.className("email"));
		var.findElement(By.tagName("input")).sendKeys("studdsfdsent8@test.com");
		WebElement var2 = driver.findElement(By.className("pass"));
		var2.findElement(By.tagName("input")).sendKeys("studsdfsent8");

		// Look for the submit button (in the login div) and click
		// to attempt to login

		WebElement submitButton = driver.findElement(By.className("btn-login"));
		submitButton.click();

		// Check that when the login is invalid, remain on same URL
		assertEquals("https://hibersense.com/access/login", driver.getCurrentUrl());

	}
}
