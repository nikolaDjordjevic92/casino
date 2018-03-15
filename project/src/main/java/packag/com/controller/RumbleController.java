package packag.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RumbleController {
	
	@RequestMapping("/rumble")
	public ModelAndView getRumble() {
		return new ModelAndView("rumble");
	}
}
