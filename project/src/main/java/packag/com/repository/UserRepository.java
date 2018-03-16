package packag.com.repository;

import org.springframework.data.repository.CrudRepository;

import packag.com.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
}
