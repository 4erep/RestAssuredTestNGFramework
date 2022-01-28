package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.Token;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String token, Object requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {

        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API + Token).
                then().spec(getResponseSpec()).
                extract().response();

    }


    public static Response get(String path, String token) {
        return given().
                spec(getRequestSpec()).
                auth().oauth2(token).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String token, Object requestPlaylist) {
        return given(getRequestSpec()).
                auth().oauth2(token).
                body(requestPlaylist).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }
}
