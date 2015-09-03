package springapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EnterController {
	
	@RequestMapping(value="/enter.html")
	public ModelAndView enterView(){
	    
		ModelAndView mav = new ModelAndView("enter");
		
		
	    
	    return mav;
	}

}
