// Java Program to Illustrate Regular Controller

package rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	private void save() throws IOException {
		this.cleanEService.save();
	}

	@GetMapping
	public Leaderboard getLeaderboard() {
	  return this.cleanEService.getLeaderboard();
	}

	@GetMapping(path = "/getUsers")
	public List<User> getUsers() {
		getLeaderboard().sortList();
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

	@DeleteMapping(path = "/removeTaskByUUID/{uuid}")
	public void removeTaskByUUID(@PathVariable("uuid") String uuid) throws IOException {
		for (User u : getLeaderboard().getUsers()) {
			if (u.getTaskByUUID(uuid)!=null) {
				u.removeTaskByUUID(uuid);
			}
		}
		save();
	}

	@PutMapping(path = "/addTask/{name}")
	public void addTask(@RequestBody Task task, @PathVariable("name") String name) throws IOException {
		getLeaderboard().getUser(name).addTask(task);
		save();
	}

	@PostMapping(path = "/addUser")
	public void addUser(@RequestBody User user) throws IOException {
		System.out.println(user);
		getLeaderboard().addUser(user);
		save();
	}

	@PutMapping(path = "/addPoints/{name}")
	public void addPoints(@RequestBody int additionalPoints, @PathVariable("name") String name) throws IOException {
		getLeaderboard().getUser(name).addPoints(additionalPoints);
		save();
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
