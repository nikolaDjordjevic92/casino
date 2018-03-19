package packag.com.restController;


import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import packag.com.domain.Combination;
import packag.com.domain.User;
import packag.com.service.GameService;
import packag.com.service.UserService;

@RestController
public class GameRestController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private UserService userService;
	
	private int counter=0;
	private int[] randomNumbers=new int[49];
	private String combinationNums;
	int winCounter = 0;
	int bet=0;
	Integer[] combinationNumsArray = new Integer[6];
	
	@RequestMapping("/game-numbers")
	public ResponseEntity<int[]> getNumbers(){
		int[] numbers = new int[49];
		for(int i=0;i<49;i++) {
			numbers[i]=i+1;
		}
		return new ResponseEntity<>(numbers,HttpStatus.OK);
	}
	
	@PostMapping(value="/game-ready",produces = "application/json",consumes="application/json")
	public ResponseEntity<Void> getStart(@RequestBody Combination combination) {
		if(combination.getNumbers().length()!=0) {
			User userFromFront = new User();
			userFromFront.setId(1);
			combination.setUser(userFromFront);
			gameService.saveCombination(combination);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping(value="/getCombinationNumbers")
	public ResponseEntity<Integer[]> getCombinationNumbers(){
		Combination combination= gameService.getCombination(1);
		if(combination==null)
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		Optional<User> user = userService.getUser(1);
		user.get().setCredit(user.get().getCredit()-combination.getBet());
		userService.updateUser(user.get());
		bet = combination.getBet();
		combinationNums=combination.getNumbers();
		String array = combination.getNumbers();
		String[] arrayString = array.split(",");
		for(int i = 0;i < arrayString.length;i++)        
		{
		    try 
		    {
		    	combinationNumsArray[i] = Integer.parseInt(arrayString[i]);
		    }
		    catch (NumberFormatException nfe)   
		    {
		    	combinationNumsArray[i] = null;
		    }
		}
		
		return new ResponseEntity<>(combinationNumsArray, HttpStatus.OK);
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
		for (int i = 0; i < combinationNumsArray.length; i++) {
			if(combinationNumsArray[i]==randomNumber)
				winCounter++;
		}
		if(winCounter==6) {
			int multiplier=0;
			multiplier=switcher(counter, multiplier);
			Optional<User> user = userService.getUser(1);
			user.get().setCredit(user.get().getCredit()+multiplier*bet);
			userService.updateUser(user.get());
			winCounter=1000;
		}
		counter++;
		if(counter==35) {
			counter=0;
			bet=0;
			winCounter=0;
		}
		return new ResponseEntity<>(randomNumber,HttpStatus.OK);
		
	}
	
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	private int switcher(int counter,int multiplier) {
		switch (counter) {
			case 5:
				multiplier=10000;
				break;
			case 6:
				multiplier=7500;
				break;
			case 7:
				multiplier=5000;
				break;
			case 8:
				multiplier=2500;
				break;
			case 9:
				multiplier=1000;
				break;
			case 10:
				multiplier=500;
				break;
			case 11:
				multiplier=300;
				break;
			case 12:
				multiplier=200;
				break;
			case 13:
				multiplier=150;
				break;
			case 14:
				multiplier=100;
				break;
			case 15:
				multiplier=90;
				break;
			case 16:
				multiplier=80;
				break;
			case 17:
				multiplier=70;
				break;
			case 18:
				multiplier=60;
				break;
			case 19:
				multiplier=50;
				break;
			case 20:
				multiplier=40;
				break;
			case 21:
				multiplier=30;
				break;
			case 22:
				multiplier=25;
				break;
			case 23:
				multiplier=20;
				break;
			case 24:
				multiplier=15;
				break;
			case 25:
				multiplier=10;
				break;
			case 26:
				multiplier=9;
				break;
			case 27:
				multiplier=8;
				break;
			case 28:
				multiplier=7;
				break;
			case 39:
				multiplier=6;
				break;
			case 30:
				multiplier=5;
				break;
			case 31:
				multiplier=4;
				break;
			case 32:
				multiplier=3;
				break;
			case 33:
				multiplier=2;
				break;
			case 34:
				multiplier=1;
				break;
				
	
			default:
				break;
			}
	
		return multiplier;
	}
}
