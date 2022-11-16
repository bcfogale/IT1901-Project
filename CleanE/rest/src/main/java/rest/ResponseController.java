// Java Program to Illustrate Regular Controller

package rest;

import org.springframework.beans.factory.annotation.Autowired;
// Importing required classes
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import core.Leaderboard;


// Annotation
@RestController
@RequestMapping("/ConsumeResponse")
public class ResponseController {

	@Autowired
	private CleanEService cleanEService;

	

	// @GetMapping("/get") public Leaderboard getList(Leaderboard users)
	// {

	// 	// Creating object of ConsumeResponse class
	// 	Leaderboard user = new cleanEService.getModel();
	// 	model.addAttribute("response",
	// 					data.get().getBody());
	// 	model.addAttribute("headers",
	// 					data.get().getHeaders());

	// 	return "output";
	// }
}
