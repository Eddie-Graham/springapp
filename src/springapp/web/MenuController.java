package springapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	
	@RequestMapping(value="/about.html")
	public ModelAndView enterAbout(HttpServletRequest request){

		return new ModelAndView("about");
	}
	
	@RequestMapping(value="/home.html")
	public ModelAndView enterHome(HttpServletRequest request){
	    
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/myprofile.html")
	public ModelAndView enterMyProfile(HttpServletRequest request){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		ModelAndView mav = new ModelAndView("myprofile");
		mav.addObject("username", username);
	    
		return mav;
	}
}
