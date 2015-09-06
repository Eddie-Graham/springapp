package springapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainMenuController {
	
	@RequestMapping(value="/mainMenu.html")
	public ModelAndView mainMenuView(){
	    
		ModelAndView mav = new ModelAndView("mainMenu");
		
	    
	    return mav;
	}

}