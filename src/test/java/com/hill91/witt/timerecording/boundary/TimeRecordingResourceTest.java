package com.hill91.witt.timerecording.boundary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class TimeRecordingResourceTest {

    @Test
    void testGetAllTimeRecordings() {
        given()
                .when().get("/time-recording")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testCreateTimeRecording() {
        String requestBody = """
                {
                    "description": "Test recording",
                    "startTime": "2025-12-28T09:00:00",
                    "endTime": "2025-12-28T10:00:00",
                    "projectName": "Test Project"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/time-recording")
                .then()
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("description", is("Test recording"))
                .body("projectName", is("Test Project"));
    }

    @Test
    void testCreateTimeRecordingWithoutDescription() {
        String requestBody = """
                {
                    "startTime": "2025-12-28T09:00:00",
                    "endTime": "2025-12-28T10:00:00"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/time-recording")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateTimeRecordingWithoutStartTime() {
        String requestBody = """
                {
                    "description": "Test recording",
                    "endTime": "2025-12-28T10:00:00"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/time-recording")
                .then()
                .statusCode(400);
    }

    @Test
    void testGetTimeRecordingById() {
        // First create a time recording
        String requestBody = """
                {
                    "description": "Test recording",
                    "startTime": "2025-12-28T09:00:00",
                    "endTime": "2025-12-28T10:00:00",
                    "projectName": "Test Project"
                }
                """;

        Long id = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/time-recording")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Then retrieve it
        given()
                .when().get("/time-recording/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("description", is("Test recording"));
    }

    @Test
    void testUpdateTimeRecording() {
        // First create a time recording
        String createBody = """
                {
                    "description": "Original description",
                    "startTime": "2025-12-28T09:00:00",
                    "endTime": "2025-12-28T10:00:00",
                    "projectName": "Original Project"
                }
                """;

        Long id = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/time-recording")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Then update it
        String updateBody = """
                {
                    "description": "Updated description",
                    "startTime": "2025-12-28T09:30:00",
                    "endTime": "2025-12-28T11:00:00",
                    "projectName": "Updated Project"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/time-recording/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("description", is("Updated description"))
                .body("projectName", is("Updated Project"));
    }

    @Test
    void testDeleteTimeRecording() {
        // First create a time recording
        String requestBody = """
                {
                    "description": "Test recording to delete",
                    "startTime": "2025-12-28T09:00:00",
                    "endTime": "2025-12-28T10:00:00",
                    "projectName": "Test Project"
                }
                """;

        Long id = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/time-recording")
                .then()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        // Then delete it
        given()
                .when().delete("/time-recording/" + id)
                .then()
                .statusCode(204);

        // Verify it's deleted
        given()
                .when().get("/time-recording/" + id)
                .then()
                .statusCode(404);
    }
}
