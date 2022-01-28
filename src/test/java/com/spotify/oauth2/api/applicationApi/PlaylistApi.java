package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {
    static String access_token = "BQAOC0Cib-oys1ubrCjSnBhfmJ4lwA6hkOtR3g08mYxigh-fwUb0-xR5Xp1WV4kkmWmRRgom6bsMMt47Bk0O6A0G_39dUhatl4rYOmbSVHd2neC-22PoyLhXXzF29wYSUe9_F6LVZ-5e0XP7UCO17y3mp-TLC0cNSnpGHJW-LaPIyreUY4FiFmxSW0CocxT5xXNIO2cYiCEpUZ1gMHeP9diOyuPbCDNuYnuy9RNxdryhhRR8";

    @Step
    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());

    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);

    }
}
