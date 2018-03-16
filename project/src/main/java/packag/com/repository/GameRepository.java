package packag.com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import packag.com.domain.Combination;

public interface GameRepository extends CrudRepository<Combination, Integer>{
	
	@Query("FROM Combination c WHERE c.id=(SELECT MAX(cc.id) AS id from Combination cc WHERE cc.user.id=:userId)")
	public Combination findTopById(@Param("userId") int userId);
}
