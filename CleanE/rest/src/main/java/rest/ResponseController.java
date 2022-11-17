// Java Program to Illustrate Regular Controller

package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Leaderboard;
import core.User;



// Annotation
@RestController
@RequestMapping("/ConsumeResponse")
public class ResponseController {

	@Autowired
	private CleanEService cleanEService;

	@GetMapping
	public Leaderboard getLeaderboard() {
	  return cleanEService.getLeaderboard();
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
	
}
