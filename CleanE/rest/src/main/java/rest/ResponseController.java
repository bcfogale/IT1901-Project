// Java Program to Illustrate Regular Controller

package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import core.Leaderboard;
import core.User;


// Annotation
@RestController

public class ResponseController {

	@Autowired
	private CleanEService cleanEService;

	@GetMapping
	public Leaderboard getLeaderboard() {
	  return cleanEService.getLeaderboard();
	}
/*
*	i path: /users kan man hente ut users, legge til users
*/

	//mapping the getUsers() method to /users
	@GetMapping(path = "/users")
	public List<User> getUsers(@PathVariable("users") String users) {
		List<User> allUsers = getLeaderboard().getUsers();
		if (allUsers.isEmpty()) {
			return null;
		} 
		return allUsers;
	}

}
