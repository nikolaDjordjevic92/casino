package packag.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import packag.com.domain.Message;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView pozz() {
		Message message = new Message("cao,kako je");
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("message", message);
		mav.addObject("name","Nikola");
		return mav;
	}
	
	@RequestMapping("/header")
	public ModelAndView pozzitj() {
		Message message = new Message("cao,kako je");
		ModelAndView mav = new ModelAndView("views/fragments/header");
		mav.addObject("message", message);
		mav.addObject("name","Nikola");
		return mav;
	}
	
	
}
