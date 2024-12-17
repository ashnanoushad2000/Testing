package com.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @BeforeAll
    public static void setup() {
        // Set the correct base URL for the API
        RestAssured.baseURI = "http://localhost:5000";
    }

    @Test
    public void testUpdateMedia() {
        // The valid media ID that exists in your backend
        String mediaId = "10bfb7db-c8fd-4cdf-8d3f-27fedb2c3cd2";

        // The request body matches Postman
        String requestBody = "{"
                + "\"title\": \"Performance Test\","
                + "\"author\": \"Performace Author\","
                + "\"isbn\": \"1234567890123\","
                + "\"category_id\": \"4bbff9e3-ad5c-11ef-8422-a0afbd3a6924\","
                + "\"publication_date\": \"2024-06-29\","
                + "\"publisher\": \"Publisher\","
                + "\"item_description\": \"Performance testing\""
                + "}";

        // Perform the PUT request and validate the response
        given()
            .header("Content-Type", "application/json") // Ensure Content-Type is set
            .body(requestBody) // Send the request body
        .when()
            .put("/api/media/update/" + mediaId) // Endpoint with mediaId
        .then()
            .log().all() // Logs request and response (optional for debugging)
            .statusCode(200) // Expecting HTTP 200 OK
            .body("message", equalTo("Media item updated successfully.")); // Validate response
    }
}
