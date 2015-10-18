package springapp.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;

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
		
		User user =  (User) request.getSession().getAttribute("user");
		String username = user.getUsername();
		String id = user.getId();
		
		ModelAndView mav = new ModelAndView("myprofile");
		mav.addObject("username", username);
		
		String path = System.getenv("APP_ROOT") + "/profile_images/" + id + ".png";

		File file = new File(path);
		if(file.exists())
		    mav.addObject("imagePath", "profile_images/" + id + ".png");

		return mav;
	}
}
