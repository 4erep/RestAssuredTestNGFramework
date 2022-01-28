package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) { // Если токена нет или текущее время больще чем expiry_time, то
                System.out.println("Renewing token ...");
                Response response = renewToken(); //сделать запрос на обновление токена
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in"); //вытащить из запроса значение expires_in и записать в переменную
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300); //прибавить к текущему времени время из expires_in и записать в expiry_time(и отнять 300 сек)

            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("ABORT!!! Renew Token failed");

        }
        return access_token;
    }


    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());

        Response response = RestResource.postAccount(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;

    }

    private static Response renewTokenOld3() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "c724f11d47e2494191ced8f3c4d57129");
        formParams.put("client_secret", "bf96fe6e187d4bb5bc61383ab0d85e30");
        formParams.put("refresh_token", "AQBf4Bpwq9FEuqEv-024KlyKY9-YR_2FFqRMApAlFCEl8FvZr8bdaqWdOP7DWw66f3gpicrqSfIDbyyjmMqDk2yh-5uegOY6MpeSh9GhvxLH_AOjCGU5ZbEt-Hlq00_xoU0");
        formParams.put("grant_type", "refresh_token");

        Response response = RestResource.postAccount(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;

    }

    private static Response renewTokenOld2() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "c724f11d47e2494191ced8f3c4d57129");
        formParams.put("client_secret", "bf96fe6e187d4bb5bc61383ab0d85e30");
        formParams.put("refresh_token", "AQBf4Bpwq9FEuqEv-024KlyKY9-YR_2FFqRMApAlFCEl8FvZr8bdaqWdOP7DWw66f3gpicrqSfIDbyyjmMqDk2yh-5uegOY6MpeSh9GhvxLH_AOjCGU5ZbEt-Hlq00_xoU0");
        formParams.put("grant_type", "refresh_token");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                log().all().
                when().post("/api/token").
                then().spec(getResponseSpec()).
                extract().response();

        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;

    }


    public static String renewTokenOld() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", "c724f11d47e2494191ced8f3c4d57129");
        formParams.put("client_secret", "bf96fe6e187d4bb5bc61383ab0d85e30");
        formParams.put("refresh_token", "AQBf4Bpwq9FEuqEv-024KlyKY9-YR_2FFqRMApAlFCEl8FvZr8bdaqWdOP7DWw66f3gpicrqSfIDbyyjmMqDk2yh-5uegOY6MpeSh9GhvxLH_AOjCGU5ZbEt-Hlq00_xoU0");
        formParams.put("grant_type", "refresh_token");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().post("/api/token").
                then().spec(getResponseSpec()).
                extract().response();

        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response.path("access_token");  // Выдергиваем access token из ответа и записываем его в переменную Response

    }
}
