package com.pataniqa.example;

import static org.fest.assertions.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.*;

import cucumber.api.java.*;

public class BingSteps {
	
	final Logger logger = LoggerFactory.getLogger(BingSteps.class);
	
	WebDriver driver;

	// need to quit webdriver at the end of the test or it will be left running
	
	@Before
	public void before() {
		driver = new PhantomJSDriver();
	}
	
	@After
	public void after() {
		driver.quit();
	}

	@Given("^I am on the Bing home page$")
	public void I_am_on_the_Bing_home_page(){
		logger.info("Going to the Bing home page");
        driver.get("http://www.bing.com");
	}
	
	@When("^I search for \"([^\"]*)\"$")
	public void I_search_for(String query) {
		logger.info("Submitting query " + query);
		WebElement element = driver.findElement(By.id("sb_form_q"));
		element.sendKeys(query);
        element.submit();
	}
	
	@Then("^the title should contain: \"([^\"]*)\"$")
	public void the_title_should_contain(String expected_title) {
		logger.info("Checking title contains " + expected_title);
	    assertThat(driver.getTitle()).contains(expected_title);
	}
	
}
