package packag.com.restController;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import packag.com.domain.User;
import packag.com.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id){
		Optional<User> user = userService.getUser(id);
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}
	
}
