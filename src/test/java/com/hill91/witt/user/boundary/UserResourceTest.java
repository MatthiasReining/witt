package com.hill91.witt.user.boundary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class UserResourceTest {

    @Test
    void testGetAllUsers() {
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void testCreateUser() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "email": "john.doe@example.com",
                    "password": "SecurePass123!",
                    "firstName": "John",
                    "lastName": "Doe",
                    "roles": "USER"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/users")
                .then()
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("username", is("jdoe"))
                .body("email", is("john.doe@example.com"))
                .body("firstName", is("John"))
                .body("lastName", is("Doe"))
                .body("enabled", is(true));
    }

    @Test
    void testCreateUserWithoutUsername() {
        String requestBody = """
                {
                    "email": "john.doe@example.com",
                    "password": "SecurePass123!",
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateUserWithoutEmail() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "password": "SecurePass123!",
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        String requestBody = """
                {
                    "username": "jdoe",
                    "email": "not-an-email",
                    "password": "SecurePass123!",
                    "firstName": "John",
                    "lastName": "Doe"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/users")
                .then()
                .statusCode(400);
    }

    @Test
    void testGetUserById() {
        // First create a user
        String createBody = """
                {
                    "username": "testuser",
                    "email": "test@example.com",
                    "password": "TestPass123!",
                    "firstName": "Test",
                    "lastName": "User"
                }
                """;

        Long userId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/users")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then get it by ID
        given()
                .when().get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", is(userId.intValue()))
                .body("username", is("testuser"))
                .body("email", is("test@example.com"));
    }

    @Test
    void testGetNonExistentUser() {
        given()
                .when().get("/users/999999")
                .then()
                .statusCode(404);
    }

    @Test
    void testUpdateUser() {
        // First create a user
        String createBody = """
                {
                    "username": "updatetest",
                    "email": "update@example.com",
                    "password": "UpdatePass123!",
                    "firstName": "Update",
                    "lastName": "Test"
                }
                """;

        Long userId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/users")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then update it
        String updateBody = """
                {
                    "firstName": "Updated",
                    "avatarUrl": "https://example.com/avatar.jpg"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("firstName", is("Updated"))
                .body("lastName", is("Test"))
                .body("avatarUrl", is("https://example.com/avatar.jpg"));
    }

    @Test
    void testDeleteUser() {
        // First create a user
        String createBody = """
                {
                    "username": "deletetest",
                    "email": "delete@example.com",
                    "password": "DeletePass123!",
                    "firstName": "Delete",
                    "lastName": "Test"
                }
                """;

        Long userId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/users")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Then delete it
        given()
                .when().delete("/users/" + userId)
                .then()
                .statusCode(204);

        // Verify it's gone
        given()
                .when().get("/users/" + userId)
                .then()
                .statusCode(404);
    }
}
