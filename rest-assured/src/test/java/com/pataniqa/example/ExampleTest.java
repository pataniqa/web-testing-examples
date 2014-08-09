package com.pataniqa.example;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void driveRestAPI() {
		get("https://gdata.youtube.com/feeds/api/videos/6acRHWnfZAE?alt=json")
				.then()
				.body("entry.title.$t",
						equalTo("X-Men: Days of Future Past | Official Trailer 2 [HD] | 20th Century FOX"));
	}

}
