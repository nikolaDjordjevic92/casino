package packag.com.repository;

import org.springframework.data.repository.CrudRepository;

import packag.com.domain.Combination;

public interface GameRepository extends CrudRepository<Combination, Integer>{
	
}
