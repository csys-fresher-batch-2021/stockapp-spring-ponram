package in.ponram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ponram.dto.Message;
import in.ponram.model.User;
import in.ponram.service.UserManager;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	UserManager userManager;

	/**
	 * Post method http://localhost:9090/User/Signup
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("api/v1/signup")
	public ResponseEntity<Message> registerUser(@RequestBody User user) {
		User saved = userManager.registration(user);
		Message message = new Message();
		HttpStatus httpStatus;
		if (saved != null) {
			message.setInfoMessage("Registered successfuly");
			httpStatus = HttpStatus.CREATED;
		} else {
			message.setErrorMessage("Can't able to Registered or user is already exist");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}

	/**
	 * Get method http://localhost:9090/User
	 * 
	 * @return
	 */
	@GetMapping
	public Iterable<User> list() {
		return userManager.getAllUsers();
	}

	/**
	 * Post method http://localhost:9090/User/Login
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("login")
	public ResponseEntity<Message> login(@RequestBody User user) {
		User userResult = userManager.login(user.getUsername(), user.getPassword());
		Message message = new Message();
		HttpStatus httpStatus;
		if (userResult != null) {
			message.setInfoMessage("Login successfuly");
			httpStatus = HttpStatus.OK;
		} else {
			message.setErrorMessage("Invalid user");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}

	/**
	 * Post method http://localhost:9090/User/AdminLogin
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("adminLogin")
	public ResponseEntity<Message> adminLogin(@RequestBody User user) {
		User userResult = userManager.adminLogin(user.getUsername(), user.getPassword());
		Message message = new Message();
		HttpStatus httpStatus;
		if (userResult != null) {
			message.setInfoMessage("Login successfuly");
			httpStatus = HttpStatus.OK;
		} else {
			message.setErrorMessage("Invalid user");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(message, httpStatus);
	}
}
