package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;

public class RemoteCleanEAccess {
    
    private final URI endpointBaseUri;
    private Leaderboard leaderboard;

    private static final String ACCEPT_HEADER = "Accept";
    private static final String APPLICATION_JSON = "application/json";

    private ObjectMapper objectMapper;
     

    public RemoteCleanEAccess(URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
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