
package StepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GETRequest {
	
	private Response response;
	
	@Given("I Set GET employee service api endpoint")
	public void i_set_get_employee_service_api_endpoint() {
		System.out.println("Setting GET employee service API endpoint");
		response = RestAssured.get("https://reqres.in/api/users?page=2");
	}

	@And("I Set request HEADER")
	public void i_set_request_header() {
		System.out.println("Setting request HEADER");
		int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Response: " + statusCode);
	}

	@When("Send a GET HTTP request")
	public void send_a_get_http_request() {
		// No implementation needed here, as the GET request is sent in the previous step
		System.out.println("Sending GET HTTP request");
		
	}

	@Then("I receive valid Response")
	public void i_receive_valid_response() {
		System.out.println("Receiving valid Response");
		String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
	}
	
	@Given("I Set POST employee service api endpoint")
	public void i_set_post_employee_service_api_endpoint() {
	    // No need to set the baseURI here
		RestAssured.baseURI = "https://reqres.in";
		System.out.println("Setting POST employee service API endpoint");
	  }

	@When("I Set POST request HEADER")
	public void i_set_post_request_header() {
	    // Set the POST request header and send the request
		response = RestAssured.given()
                .header("Content-Type", "application/json") // Set the Content-Type header
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"leader\"\n" +
                        "}")
                .post("/api/users");
	    
	}

	@And("Send a POST HTTP request")
	public void send_a_post_http_request() {
	    // Check the status code of the response
	    int statusCode = response.getStatusCode();
	    Assert.assertEquals(201, statusCode); // Expecting 201 (Created)
	    System.out.println("Response: " + statusCode);
	}

	@Then("I receive POST valid Response")
	public void i_receive_post_valid_response() {
	    // Validate the received response
	    System.out.println("Receiving valid Response");
	    String responseBody = response.getBody().asString();
	    System.out.println("Response Body: " + responseBody);
	
}
}