package com.hill91.witt.worker.boundary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class WorkerResourceTest {

    @Test
    void testGetAllWorkers() {
        given()
                .when().get("/workers")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testCreateWorker() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "firstName": "John",
                    "lastName": "Doe",
                    "avatarUrl": "https://example.com/avatar.jpg"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/workers")
                .then()
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("username", is("jdoe"))
                .body("firstName", is("John"))
                .body("lastName", is("Doe"))
                .body("avatarUrl", is("https://example.com/avatar.jpg"));
    }

    @Test
    void testCreateWorkerWithoutUsername() {
        String requestBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/workers")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateWorkerWithoutFirstName() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "lastName": "Doe"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/workers")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateWorkerWithoutLastName() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "firstName": "John"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/workers")
                .then()
                .statusCode(400);
    }

    @Test
    void testGetWorkerById() {
        // First create a worker
        String createBody = """
                {
                    "username": "testuser",
                    "firstName": "Test",
                    "lastName": "User"
                }
                """;

        Long workerId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/workers")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then get it by ID
        given()
                .when().get("/workers/" + workerId)
                .then()
                .statusCode(200)
                .body("id", is(workerId.intValue()))
                .body("username", is("testuser"))
                .body("firstName", is("Test"))
                .body("lastName", is("User"));
    }

    @Test
    void testGetNonExistentWorker() {
        given()
                .when().get("/workers/999999")
                .then()
                .statusCode(404);
    }

    @Test
    void testUpdateWorker() {
        // First create a worker
        String createBody = """
                {
                    "username": "updatetest",
                    "firstName": "Update",
                    "lastName": "Test"
                }
                """;

        Long workerId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/workers")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then update it
        String updateBody = """
                {
                    "firstName": "Updated",
                    "avatarUrl": "https://example.com/new-avatar.jpg"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/workers/" + workerId)
                .then()
                .statusCode(200)
                .body("firstName", is("Updated"))
                .body("lastName", is("Test"))
                .body("avatarUrl", is("https://example.com/new-avatar.jpg"));
    }

    @Test
    void testDeleteWorker() {
        // First create a worker
        String createBody = """
                {
                    "username": "deletetest",
                    "firstName": "Delete",
                    "lastName": "Test"
                }
                """;

        Long workerId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/workers")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then delete it
        given()
                .when().delete("/workers/" + workerId)
                .then()
                .statusCode(204);

        // Verify it's gone
        given()
                .when().get("/workers/" + workerId)
                .then()
                .statusCode(404);
    }
}
