package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.UserManagerInterface;

@Controller
public class MenuController {
	
	@Autowired
	private UserManagerInterface userManager;
	
	@RequestMapping(value="/about.html")
	public ModelAndView enterAbout(HttpServletRequest request){

		return new ModelAndView("about");
	}
	
	@RequestMapping(value="/home.html")
	public ModelAndView enterHome(HttpServletRequest request){
	    
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/myprofile.html")
	public ModelAndView enterMyProfile(HttpServletRequest request) throws SQLException, ParseException{
		
		User loggedInUser =  (User) request.getSession().getAttribute("user");
		
		String userId = (String) request.getParameter("id");
		User user = userManager.getUserById(userId);
		
		ModelAndView mav = new ModelAndView("myprofile");
		mav.addObject("myProfileUser", user);
		
		if(loggedInUser.getId().equals(userId))
			mav.addObject("isLoggedInUser", true);
		
		return mav;
	}
	
	@RequestMapping(value="/usermap.html")
	public ModelAndView enterUserMap(HttpServletRequest request) throws SQLException{
		
		return new ModelAndView("usermap");
	}
}
