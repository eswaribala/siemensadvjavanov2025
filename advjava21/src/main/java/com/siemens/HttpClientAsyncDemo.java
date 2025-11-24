package com.siemens;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.dtos.User;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientAsyncDemo {
    public static void main(String[] args) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(java.time.Duration.ofSeconds(10)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(java.net.URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    try {
                       User[] users= objectMapper.readValue(response, User[].class);
                        java.util.List.of(users).forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                })
                .join();



    }
}
