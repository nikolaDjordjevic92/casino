package packag.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
	
	@RequestMapping("/game")
	public ModelAndView getGame() {
		ModelAndView mav = new ModelAndView("game");
		return mav;
	}
}
