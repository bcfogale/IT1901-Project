// Java Program to Illustrate Regular Controller

package rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Leaderboard;
import core.Task;
import core.User;


// Annotation
@RestController
@RequestMapping("Leaderboard")
public class ResponseController {

	@Autowired
	private CleanEService cleanEService;

	public void save() throws IOException {
		this.cleanEService.save();
	}

	@GetMapping
	public Leaderboard getLeaderboard() {
	  return this.cleanEService.getLeaderboard();
	}

	@GetMapping(path = "/getUsers")
	public List<User> getUsers() {
		return getLeaderboard().getUsers();
	}

	@GetMapping(path = "/getUser/{name}")
	public User getUser(@PathVariable("name") String name) {
		return getLeaderboard().getUser(name);
	}

	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getTaskName")
	public String getTaskName(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getTaskName();
	}
	
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getPointsValue")
	public int getPointsValue(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getPointsValue();
	}

	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getDueDay")
	public String getDueDay(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getDueDay();
	}

	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/isCompleted")
	public Boolean isCompleted(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).isCompleted();
	}

	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getAssignedUser")
	public User getAssignedUser(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getAssignedUser();
	}

	@DeleteMapping(path = "/getUser/{name}/removeTaskByUUID/{uuid}")
	public void removeTaskByUUID(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		getLeaderboard().getUser(name).removeTaskByUUID(uuid);
	}

	@PostMapping(path = "/getUser/{name}/addTask/{task}")
	public void addTask(@PathVariable("name") String name, @PathVariable("task") Task task) {
		getLeaderboard().getUser(name).addTask(task);
	}

	@PostMapping(path = "/addUser/{user}")
	public void addUser(@PathVariable("user") User user) {
		getLeaderboard().addUser(user);
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
