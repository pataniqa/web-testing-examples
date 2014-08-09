package com.pataniqa.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java.en.*;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.response.*;

public class YoutubeSteps {

	final Logger logger = LoggerFactory.getLogger(YoutubeSteps.class);
	private String youtube_url;
	private Response json_response;

	@Given("^I query video by \"(.*)?\"$")
	public void I_query_video_by(String key) {
		youtube_url = "https://gdata.youtube.com/feeds/api/videos/" + key + "?alt=json";
		logger.info("http query = " + youtube_url);
	}

	@When("^I make the rest call$")
	public void I_make_the_rest_call() {
		json_response = get(youtube_url);
		logger.info("Response = " + json_response.toString());
	}

	@Then("^title should be \"(.+)\"")
	public void title_should_be(String expected_title) {
		logger.info("Checking title is " + expected_title);
		json_response.then()
		.body("entry.title.$t",
				equalTo(expected_title));
	}
	
	@Then("^category should be \"(.+)\"")
	public void category_should_be(String expected_category) {
		logger.info("Checking category is " + expected_category);
		json_response.then()
		.body("entry.media$group.media$category.label",
				equalTo(expected_category));
	}
}
