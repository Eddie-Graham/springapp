package springapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		return "redirect:login.html";
	}
	
	@RequestMapping(value="/about.html")
	public ModelAndView enterabout(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:login.html");

		return new ModelAndView("about");
	}
	
	@RequestMapping(value="/home.html")
	public ModelAndView enterhome(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:login.html");

		return new ModelAndView("home");
	}

}
