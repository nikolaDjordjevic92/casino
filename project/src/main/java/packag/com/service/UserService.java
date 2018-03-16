package packag.com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import packag.com.domain.User;
import packag.com.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> getUser(int id) {
		return userRepository.findById(id);
	}
	
}
