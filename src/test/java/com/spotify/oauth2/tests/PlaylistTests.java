package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {

    @Step
    public Playlist playlistBuilder(String name, String description, boolean ispublic) {
        return Playlist.builder().
                name(name).
                description(description).
                _public(ispublic).build();

    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist request_playlist) {
        assertThat(responsePlaylist.getName(), equalTo(request_playlist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(request_playlist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(request_playlist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public void assertError(Error responseError, int expectedStatusCode, String expectedMessage) {
        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));

    }

    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Story("Create a playlist story")
    @Description("this is the description")
    @Test(description = "Should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlayList() {
        Playlist request_playlist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.post(request_playlist);

        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());

        assertPlaylistEqual(response.as(Playlist.class), request_playlist);


    }

    @Test
    public void ShouldBeAbleToGetAPlayList() {
        Playlist request_playlist = playlistBuilder("New Playlist5", "New playlist description5", false);


        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());

        assertPlaylistEqual(response.as(Playlist.class), request_playlist);


    }

    @Test
    public void ShouldBeAbleToUpdateAPlayList() {
        Playlist request_playlist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), request_playlist);
        assertThat(response.statusCode(), equalTo(200));

    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithoutName() {
        Playlist request_playlist = playlistBuilder("", generateDescription(), false);


        Response response = PlaylistApi.post(request_playlist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());

        assertError(response.as(Error.class), StatusCode.CODE_400.getCode(), StatusCode.CODE_400.getMessage());

    }

    @Story("Create a playlist story")
    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken() {
        Playlist request_playlist = playlistBuilder(generateName(), generateDescription(), false);
        String invalid_token = "12345";

        Response response = PlaylistApi.post(invalid_token, request_playlist);

        assertStatusCode(response.statusCode(), StatusCode.CODE_401.getCode());
        assertError(response.as(Error.class), StatusCode.CODE_401.getCode(), StatusCode.CODE_401.getMessage());


    }
}
