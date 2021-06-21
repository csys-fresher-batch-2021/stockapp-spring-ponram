package in.ponram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ponram.dao.UserRepository;
import in.ponram.model.User;
import in.ponram.validator.UserManagerValidation;

@Service
public class UserManager {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used to call user validation class Then the validation is
	 * success call addUser method to store the details
	 * 
	 * @param user
	 * @return true or false
	 */
	public User registration(User user) {

		User save = null;
		if (UserManagerValidation.isValideUserDetails(user)) {
			save = userRepository.save(user);
		}
		return save;
	}

	/**
	 * This method is used to call the call login validation to verify the user
	 * 
	 * @param userName
	 * @param password
	 * @return true if the user exists
	 * @throws Exception
	 */
	public User login(String userName, String password) {

		User user = userRepository.findByUsername(userName).orElse(null);
		
		return user != null && UserManagerValidation.isValidLogin(user, password) ? user : null;
	}

	/**
	 * This method is used to call the call Admin login validation to verify the
	 * user is admin
	 * 
	 * @param userName
	 * @param password
	 * @return true if the user exists
	 * @throws Exception
	 */
	public User adminLogin(String userName, String password) {

		User admin = userRepository.findByUsername(userName).orElse(null);
		
		return admin != null && UserManagerValidation.isAdminUser(admin, password) ? admin : null;
	}

	public Iterable<User> getAllUsers() {
		
		return userRepository.findAll();
	}
}
