package com.sparta.lb.APITests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class SinglePostcodeTests {

    private static HttpResponse<String> httpResponse = null;
    private static JSONObject jsonObject = null;
    @BeforeAll
    public static void oneTimeSetUp() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/EC2Y5AS"))
                .setHeader("Content-type", "application/json")
                .build();
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject)jsonParser.parse(httpResponse.body());
//            System.out.println(jsonObject.get("status"));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("URI Path")
    public void testUriPath(){
        Assertions.assertEquals("/postcodes/EC2Y5AS", httpResponse.uri().getPath());
    }

    @Test
    @DisplayName("URI")
    public void testUri(){
        Assertions.assertEquals("https://api.postcodes.io/postcodes/EC2Y5AS", httpResponse.uri().toString());
    }

    @Test
    @DisplayName("Longitude")
    public void testLongitude(){
        var result = (JSONObject)jsonObject.get("result");
        Assertions.assertEquals(-0.09392, result.get("longitude"));
    }

    @Test
    @DisplayName("Status Code")
    public void testStatusCode(){
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @DisplayName("Header Server is cloudflare")
    public void testHeaderServer(){
        Assertions.assertTrue(httpResponse.headers().map().get("Server").contains("cloudflare"));
    }

    @Test
    @DisplayName("Status is 200")
    public void testStatus(){
        Assertions.assertEquals(200L, jsonObject.get("status"));
    }

    @Test
    @DisplayName("URI Test invalid postcode")
    public void testResponseStatusFailCode() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/fsvf"))
                .setHeader("Content-type", "application/json")
                .build();
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(404, httpResponse.statusCode());
    }

    @Test
    @DisplayName("URI Test no postcode")
    public void testResponseStatusEmptyCode() {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/"))
                .setHeader("Content-type", "application/json")
                .build();
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(400, httpResponse.statusCode());
    }
}

