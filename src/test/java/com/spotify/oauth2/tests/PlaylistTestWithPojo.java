//package com.spotify.oauth2.tests;
//
//import com.spotify.oauth2.pojo.Error;
//import com.spotify.oauth2.pojo.Playlist;
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
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class PlaylistTestWithPojo {
//    RequestSpecification requestSpecification;
//    ResponseSpecification responseSpecification;
//    String access_token = "BQCTdLzS97SGyQoF4gu2Op4jgY-gZaDjoPgY5Qj2EolZEnzOxnV_darUtCym_u0o2X-iC_bp0hh13bYswHDvXHS-Xwid2F7MhK4dSMMXPm_0UojbcNq2vXjgOf8hVBwQPL_Uw6chjZx52BBh8nhAZ9KURZJYbVdW6o0O9TnhY6isG5hR10VDcu5rpItDQOuavFuY4-FtChR2Qffq18hjXLatHO5duFfDNpp1rtVccjdoL8q_";
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
//        Playlist request_playlist = new Playlist().
//                setName("New Playlist2").
//                setDescription("New playlist description2").
//                setPublic(false);
//
//        Playlist responsePlaylist = given(requestSpecification).
//                body(request_playlist).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(201).
//                extract().
//                as(Playlist.class);
//
//        assertThat(responsePlaylist.getName(), equalTo(request_playlist.getName()));
//        assertThat(responsePlaylist.getDescription(), equalTo(request_playlist.getDescription()));
//        assertThat(responsePlaylist.getPublic(), equalTo(request_playlist.getPublic()));
//
//    }
//
//    @Test
//    public void ShouldBeAbleToGetAPlayList() {
//        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("New Playlist");
//        requestPlaylist.setDescription("New playlist description");
//        requestPlaylist.setPublic(false);
//
//
//        Playlist responsePlaylist = given().
//                spec(requestSpecification).
//                when().
//                get("/playlists/3WViQe2mbpKHA1wqd0TVO5").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(200).
//                extract().response().as(Playlist.class);
//        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
//        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
//        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
//
//    }
//
//    @Test
//    public void ShouldBeAbleToUpdateAPlayList() {
//        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("New Playlist3");
//        requestPlaylist.setDescription("New playlist description3");
//        requestPlaylist.setPublic(false);
//
//        given().
//                spec(requestSpecification).
//                body(requestPlaylist).
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
//        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("");
//        requestPlaylist.setDescription("New playlist description");
//        requestPlaylist.setPublic(false);
//
//
//        Error error = given().
//                spec(requestSpecification).
//                body(requestPlaylist).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(400).
//                extract().as(Error.class);
//
//        assertThat(error.getError().getStatus(), equalTo(400));
//        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
//
//    }
//
//    @Test
//    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken() {
//        Playlist requestPlaylist = new Playlist();
//        requestPlaylist.setName("");
//        requestPlaylist.setDescription("New playlist description");
//        requestPlaylist.setPublic(false);
//
//        Error error = given().
//                baseUri("https://api.spotify.com").
//                basePath("/v1").
//                header("Authorization", "Bearer " + "12345").
//                contentType(ContentType.JSON).
//                log().all().
//                body(requestPlaylist).
//                when().
//                post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
//                then().
//                spec(responseSpecification).
//                assertThat().
//                statusCode(401).
//                extract().as(Error.class);
//
//        assertThat(error.getError().getStatus(), equalTo(401));
//        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
//
//
//    }
//
//
//}
