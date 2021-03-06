package packag.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import packag.com.domain.Combination;
import packag.com.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public Combination getCombination(int user_id) {
		return gameRepository.findTopById(user_id);
	}
	
	public void saveCombination(Combination combination) {
		gameRepository.save(combination);
	}
}
