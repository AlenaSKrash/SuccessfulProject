package com.sparta.lb.apiclient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.Map;

public class SimpleClient {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.postcodes.io/postcodes/CH614UY"))//                .setHeader("Content-type", "application/json")
                .build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse);
            System.out.println("URL: " + httpResponse.uri());
            System.out.println("Headers: " + httpResponse.headers());
            System.out.println("Status code: " + httpResponse.statusCode());
            System.out.println();
            Map<String, List<String>> headers = httpResponse.headers().map();
            for (var item : headers.entrySet()) {
                System.out.print(item.getKey() + " ");
                System.out.println(item.getValue());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Interrogating the response body using simple-json");
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(httpResponse.body());
            System.out.println(jsonObject.get("status"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}