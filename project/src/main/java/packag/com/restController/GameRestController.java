package packag.com.restController;


import java.util.Arrays;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import packag.com.domain.Combination;
import packag.com.service.GameService;

@RestController
public class GameRestController {

	@Autowired
	private GameService gameService;
	
	private int counter=0;
	private int[] randomNumbers=new int[49];
	
	@RequestMapping("/game-numbers")
	public ResponseEntity<int[]> getNumbers(){
		int[] numbers = new int[49];
		for(int i=0;i<49;i++) {
			numbers[i]=i+1;
		}
		return new ResponseEntity<>(numbers,HttpStatus.OK);
	}
	
	@PostMapping(value="/game-ready",produces = "application/json",consumes="application/json")
	public ResponseEntity<Void> getStart(@RequestBody int[] numbers) {
		if(numbers.length<7) {
			Combination combination = new Combination();
			combination.setNumbers(Arrays.toString(numbers));
			gameService.saveCombination(combination);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping(value="/getCombinationNumbers")
	public ResponseEntity<String> getCombinationNumbers(){
		Iterable<Combination> combinationList= gameService.getCombination();
		if(combinationList==null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		Combination combination = getLastElement(combinationList);
		String array = combination.getNumbers();
		
		return new ResponseEntity<>(array, HttpStatus.OK);
	}
	
	@GetMapping(value="/getRandomNumber/{ball}")
	public ResponseEntity<Integer> getRandomNumber(@PathVariable("ball") Integer number){
		if(number!=counter && counter>34)
			return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		int randomNumber=0;
		a:while(true) {
			randomNumber=randomWithRange(1, 49);
			if(randomNumbers.length<1)
				break;
			else {
				for (int i = 0; i < randomNumbers.length; i++) {
					if(randomNumbers[i]==randomNumber)
						continue a;
				}
			}
			break;
		}
		randomNumbers[counter]=randomNumber;
		counter++;
		if(counter==35)
			counter=0;
		return new ResponseEntity<>(randomNumber,HttpStatus.OK);
		
	}
	
	private static <T> T getLastElement(final Iterable<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement=itr.next();
        }

        return lastElement;
	}
	
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
}
