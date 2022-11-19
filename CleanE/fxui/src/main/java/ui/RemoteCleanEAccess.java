package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Leaderboard;
import core.Task;
import core.User;
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
    /*
    * methods
    */
    
    private void addTask(User u, Task t, String method) {
        try {
            u.addTask(t);
            String json = objectMapper.writeValueAsString(u);
            HttpRequest request = HttpRequest.newBuilder(setURI(method))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .PUT(BodyPublishers.ofString(json))
            .build();
            
            HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void addUser(User u, String method) {
        try {
            String json = objectMapper.writeValueAsString(u);
            HttpRequest request = HttpRequest.newBuilder(setURI(method))
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
    
    private void removeTaskByUUID(Task t, String method) {
        try {
            // Task t = null;
            
            // for (User u : getLeaderboard().getUsers()) {
                //     t = u.getTaskByUUID(uuid);
                //     u.removeTaskByUUID(uuid);
                // }
                // String json = objectMapper.writeValueAsString(t);
                HttpRequest request = HttpRequest.newBuilder(setURI(method +"/"+ t.getUuid()))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .header(t.getUuid(), APPLICATION_JSON)
                .DELETE()
                .build();
                
                HttpResponse<String> response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        private List<User> getUsers(String method) {
            try {
                HttpRequest request = HttpRequest.newBuilder(setURI(method))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
                
                HttpResponse<String> response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
                
                List<User> users = new ArrayList<User>();
                
                for (Object object : objectMapper.readValue(response.body(), List.class)) {
                    users.add((User) object);
                }
                
                return users;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        private User getUser(String method) {
            try {
                HttpRequest request = HttpRequest.newBuilder(setURI(method))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
            .GET()
            .build();
            
            HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            
            User user = objectMapper.readValue(response.body(), User.class);
            
            return user;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Task getTask(String method) {
        try {
            HttpRequest request = HttpRequest.newBuilder(setURI(method))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .GET()
            .build();
            
            HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            
            Task task = objectMapper.readValue(response.body(), Task.class);
            
            return task;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    
    public Leaderboard getLeaderboard() { //når og hvor skal denne kalles?
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
    
    public void addTask(User u, Task t) {
        addTask(u, t, "/addTask");
    }
    
    public void addUser(User u) {
        addUser(u, "/addUser");
    }

    public void removeTaskByUUID(Task t) {
        removeTaskByUUID(t, "/removeTaskByUUID");
    }

    public List<User> getUsers() {
        return getUsers("/getUsers");
    }

    public User getUser() {
        return getUser("/getUser");
    }

    public Task getTask() {
        return getTask("/getTask");
    }


}