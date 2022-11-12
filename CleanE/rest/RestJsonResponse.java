package rest;

import java.util.ArrayList;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Leaderboard;
 
@RestController
@RequestMapping(path="/JSON", produces="application/json")
@CrossOrigin(origins="*")

public class RestJsonResponse {
    
    /*@GetMapping("/{id}/{points}/{name}/{tasks}")
    public ResponseEntity<Leaderboard> getData(@PathVariable("id") String id,
                             @PathVariable("points") int points,
                                @PathVariable("name") String name,
                                    @PathVariable("tasks") String tasks) {
         
        Leaderboard user = new Leaderboard();
        user.setId(id);
        user.setPoints(points);
        user.setName(name);
        user.setTasks(tasks);*/

    @GetMapping("/{users}")
    public ResponseEntity<Leaderboard> getData(@PathVariable("users") List users){

        Leaderboard user = new Leaderboard();
        user.setUsers(users);
         
        HttpHeaders headers = new HttpHeaders();
         
        ResponseEntity<Leaderboard> entity = new ResponseEntity<>(user,headers,HttpStatus.CREATED);
         
        return entity;
    }
}
