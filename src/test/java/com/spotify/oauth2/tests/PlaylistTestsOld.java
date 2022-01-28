//package com.spotify.oauth2.tests;
//
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.filter.log.LogDetail;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.equalTo;
//
//public class PlaylistTests {
//
//    RequestSpecification requestSpecification;
//    ResponseSpecification responseSpecification;
//    String access_token = "BQAnA2j3qBaFLn2R5ETSavXvdGw3wDS8GFt0XIi7Uz2iRbUNvwkpuHPI5SSE3SYWgWnocI-sU8shJmKgjYqAp_KFd8E5I_v5Khk0m-ZfFX5Np3GFrITcLIbWpU4n9nUtWBfFjXlI_IyQooWujXZ53QvyXnI6W-fyhwOj93rRgcyxIHB4AQphyhFqWGsv78RunDtRYlZI7DYjEguvR9Lb0rUAN4ruF_kSeCg2GWlT_ZK8I_Qq";
//
//    @BeforeClass
//    public void beforeClass() {
//        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
//                setBaseUri("https://api.spotify.com").
//                setBasePath("/v1").
//                addHeader("Authorization", "Bearer " + access_token).
//                setContentType(ContentType.JSON).
//                log(LogDetail.ALL);
//
//        requestSpecification = requestSpecBuilder.build();
//
//        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
//                expectContentType(ContentType.JSON).
//                log(LogDetail.ALL);
//        responseSpecification = responseSpecBuilder.build();
//    }
//
//    @Test
//    public void ShouldBeAbleToCreateAPlayList() {
//        String payload = "{\n" +
//                "  \"name\": \"New Playlist\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";
//        given().
//                spec(requestSpecification).
//                body(payload).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(201).
//                body("name", equalTo("New Playlist"),
//                        "description", equalTo("New playlist description"),
//                        "public", equalTo(false));
//
//    }
//
//    @Test
//    public void ShouldBeAbleToGetAPlayList() {
//        given().
//                spec(requestSpecification).
//                when().
//                get("/playlists/3WViQe2mbpKHA1wqd0TVO5").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(200).
//                body("name", equalTo("New Playlist"),
//                        "description", equalTo("New playlist description"),
//                        "public", equalTo(false));
//
//    }
//
//    @Test
//    public void ShouldBeAbleToUpdateAPlayList() {
//        String payload = "{\n" +
//                "  \"name\": \"New Playlist1\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";
//
//        given().
//                spec(requestSpecification).
//                body(payload).
//                when().
//                put("/playlists/3WViQe2mbpKHA1wqd0TVO5").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(200);
//
//
//    }
//
//    @Test
//    public void ShouldNotBeAbleToCreateAPlayListWithoutName() {
//        String payload = "{\n" +
//                "  \"name\": \"New Playlist\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";
//        given().
//                spec(requestSpecification).
//                body(payload).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(400).
//                body("error.status", equalTo(400),
//                        "error.message", equalTo("Missing required field: name"));
//
//
//    }
//
//    @Test
//    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken() {
//        String payload = "{\n" +
//                "  \"name\": \"\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";
//        given().
//                baseUri("https://api.spotify.com").
//                basePath("/v1").
//                header("Authorization", "Bearer " + "12345").
//                contentType(ContentType.JSON).
//                log().all().
//                body(payload).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(401).
//                body("error.status", equalTo(401),
//                        "error.message", equalTo("Invalid access token"));
//
//
//    }
//
//
//}
