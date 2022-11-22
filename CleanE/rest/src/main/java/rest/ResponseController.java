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

	/**
	 * Metode for å lagre.
	 */
	private void save() throws IOException {
		this.cleanEService.save();
	}

	/**
	 * Mapper getter for Leaderboard.
	 */
	@GetMapping
	public Leaderboard getLeaderboard() {
	  return this.cleanEService.getLeaderboard();
	}

	/**
	 * Mapper getter en liste med brukere.
	 */
	@GetMapping(path = "/getUsers")
	public List<User> getUsers() {
		getLeaderboard().sortList();
		return getLeaderboard().getUsers();
	}

	/**
	 * Mapper getter for User.
	 */
	@GetMapping(path = "/getUser/{name}")
	public User getUser(@PathVariable("name") String name) {
		return getLeaderboard().getUser(name);
	}

	/**
	 * Mapper getter for navnet til en Task.
	 */
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getTaskName")
	public String getTaskName(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getTaskName();
	}
	
	/**
	 * Mapper getter for poengverdien til en Task.
	 */
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getPointsValue")
	public int getPointsValue(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getPointsValue();
	}

	/**
	 * Mapper getter for dagen en Task skal gjøres.
	 */
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getDueDay")
	public String getDueDay(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getDueDay();
	}

	/**
	 * Mapper getter for om en Task er fullført eller ikke.
	 */
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/isCompleted")
	public Boolean isCompleted(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).isCompleted();
	}

	/**
	 * Mapper getter for brukeren som en Task tilhører.
	 */
	@GetMapping(path = "/getUser/{name}/getTaskByUUID/{uuid}/getAssignedUser")
	public User getAssignedUser(@PathVariable("name") String name, @PathVariable("uuid") String uuid) {
		return getLeaderboard().getUser(name).getTaskByUUID(uuid).getAssignedUser();
	}

	/**
	 * Mapper slettemetode for en task ved hjelp av uuid.
	 */
	@DeleteMapping(path = "/removeTaskByUUID/{uuid}")
	public void removeTaskByUUID(@PathVariable("uuid") String uuid) throws IOException {
		for (User u : getLeaderboard().getUsers()) {
			if (u.getTaskByUUID(uuid)!=null) {
				u.removeTaskByUUID(uuid);
			}
		}
		save();
	}

	/**
	 * Mapper metode som legger til task.
	 */
	@PutMapping(path = "/addTask/{name}")
	public void addTask(@RequestBody Task task, @PathVariable("name") String name) throws IOException {
		getLeaderboard().getUser(name).addTask(task);
		save();
	}

	/**
	 * Mapper metode som legger til user.
	 */
	@PostMapping(path = "/addUser")
	public void addUser(@RequestBody User user) throws IOException {
		System.out.println(user);
		getLeaderboard().addUser(user);
		save();
	}

	/**
	 * Mapper metode som legger til poeng.
	 */
	@PutMapping(path = "/addPoints/{name}")
	public void addPoints(@RequestBody int additionalPoints, @PathVariable("name") String name) throws IOException {
		getLeaderboard().getUser(name).addPoints(additionalPoints);
		save();
	}
	


	/**
	 * Mapper getUsers() metoden til /users
	 */
	@GetMapping(path = "/users")
	public List<User> getUsers(@PathVariable("users") String users) {
		List<User> allUsers = getLeaderboard().getUsers();
		if (allUsers.isEmpty()) {
			return null;
		} 
		return allUsers;
	}

}
