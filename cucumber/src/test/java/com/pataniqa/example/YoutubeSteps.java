package com.pataniqa.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
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

	@Given("^I query movie by \"(.*)?\"$")
	public void I_query_movie_by_title(String key) throws UnsupportedEncodingException {
		youtube_url = "https://gdata.youtube.com/feeds/api/videos/" + key + "?alt=json";
		logger.info("http query = " + youtube_url);
	}

	@When("^I make the rest call$")
	public void I_make_the_rest_call() throws IOException, JSONException {
		json_response = get(youtube_url);
		logger.info("Response = " + json_response.toString());
	}

	@Then("^title should be \"(.+)\"")
	public void response_should_contain_JSON(String expected_title) throws JSONException {
		logger.info("Checking title is " + expected_title);
		json_response.then()
		.body("entry.title.$t",
				equalTo(expected_title));
	}
	
	@Then("^category should be \"(.+)\"")
	public void response_should_contain(String expected_category) throws JSONException {
		logger.info("Checking category is " + expected_category);
		json_response.then()
		.body("entry.media$group.media$category.label",
				equalTo(expected_category));
	}
}
