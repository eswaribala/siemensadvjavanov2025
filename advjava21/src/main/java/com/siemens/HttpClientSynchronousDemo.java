package com.siemens;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.dtos.User;
import com.siemens.models.Vehicle;
import org.json.JSONArray;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class HttpClientSynchronousDemo {
    public static void main(String[] args) {
    //create http
        HttpClient httpClient=HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(100))
                .version(HttpClient.Version.HTTP_2)
                .build();
        // create HttpRequest
        HttpRequest httpRequest=HttpRequest.newBuilder()
                .GET()
                .uri(java.net.URI.create("https://jsonplaceholder.typicode.com/users"))
                .build();
        HttpResponse<String> httpResponse;
        //JSONArray jsonArray;
        HttpHeaders httpHeaders;
        ObjectMapper objectMapper=new ObjectMapper();
        try{
           httpResponse=  httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
           httpHeaders= httpResponse.headers();
              httpHeaders.map().entrySet().forEach((entry)->{
                System.out.println(entry.getKey()+ ": " + entry.getValue());
              });
              /*jsonArray=new JSONArray(httpResponse.body());
                for (int i = 0; i < jsonArray.length(); i++) {
                    System.out.println(jsonArray.getJSONObject(i).getString("name"));
                }*/
           User[] users= objectMapper.readValue(httpResponse.body(), User[].class);
            List.of(users).forEach(System.out::println);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
