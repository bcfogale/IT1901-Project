// Java Program to Illustrate Regular Controller

package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Leaderboard;


// Annotation
@RestController
@RequestMapping("/ConsumeResponse")
public class ResponseController {

	@Autowired
	private CleanEService cleanEService;

	private Leaderboard name() {
		return new Leaderboard();
	}

	@GetMapping
	public Leaderboard getLeaderboard() {
	  return cleanEService.getLeaderboard();
	}
  

	// 	// Creating object of ConsumeResponse class
	// 	Leaderboard user = new cleanEService.getModel();
	// 	model.addAttribute("response",
	// 					data.get().getBody());
	// 	model.addAttribute("headers",
	// 					data.get().getHeaders());

	// 	return "output";
	// }
}
