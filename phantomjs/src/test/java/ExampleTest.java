import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class ExampleTest {

	@Test
	public void checkWeCanDriveGoogle() {
		WebDriver driver = new PhantomJSDriver();
		driver.get("http://www.google.com");

		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Cheese!");
		element.submit();

		assertEquals("Cheese! - Google Search", driver.getTitle());

		driver.quit();
	}

}
