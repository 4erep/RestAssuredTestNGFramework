package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlaylistApiOLD {
    static String access_token = "BQAOC0Cib-oys1ubrCjSnBhfmJ4lwA6hkOtR3g08mYxigh-fwUb0-xR5Xp1WV4kkmWmRRgom6bsMMt47Bk0O6A0G_39dUhatl4rYOmbSVHd2neC-22PoyLhXXzF29wYSUe9_F6LVZ-5e0XP7UCO17y3mp-TLC0cNSnpGHJW-LaPIyreUY4FiFmxSW0CocxT5xXNIO2cYiCEpUZ1gMHeP9diOyuPbCDNuYnuy9RNxdryhhRR8";

    public static Response post(Playlist requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + access_token).
                when().post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + token).
                when().post("/users/56no5qmoaeuso8wu779irs1k2/playlists").
                then().spec(getResponseSpec()).
                extract().response();
    }


    public static Response get(String playlistId) {
        return given().
                spec(getRequestSpec()).
                when().
                get("/playlists/" + playlistId).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return given().
                spec(getRequestSpec()).
                body(requestPlaylist).
                when().
                put("/playlists/" + playlistId).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

}
