package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;
import core.Task;
import json.CleanEModule;

public class RemoteCleanEAccess {
    
    private URI endpointBaseUri;
    private Leaderboard leaderboard;

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String APPLICATION_JSON = "application/json";

    private ObjectMapper objectMapper;
     

    public RemoteCleanEAccess(URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.registerModule(new CleanEModule());
    }

    private URI setURI(String s) {
       return endpointBaseUri.resolve(endpointBaseUri + s);
    }

    private void addTask(Task t, String s) {
        try {
            String json = objectMapper.writeValueAsString(t);
            HttpRequest request = HttpRequest.newBuilder(setURI(s))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .POST(BodyPublishers.ofString(json))
            .build();

            HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeTaskByUUID(String uuid, String s) {
        try {
            String json = objectMapper.writeValueAsString(t);
            HttpRequest request = HttpRequest.newBuilder(setURI(s))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .POST(BodyPublishers.ofString(json))
            .build();

            HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * methods
    */

    private Leaderboard getLeaderboard() { //når og hvor skal denne kalles?
        if (leaderboard == null) {
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
            .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
            
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                this.leaderboard = objectMapper.readValue(response.body(), Leaderboard.class);
            }
            catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return leaderboard;
    }
  
}