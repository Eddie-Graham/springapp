package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

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
		
		String userId = (String) request.getParameter("id");
		
		ModelAndView mav = new ModelAndView("myprofile");
		
		User loggedInUser =  (User) request.getSession().getAttribute("user");
		
		if(loggedInUser.getId().equals(userId))
			mav.addObject("isLoggedInUser", true);
		// if logged in user is not viewing their own profile, increment profile views
		else 
			userManager.incrementProfileViews(userId);
		
		User user = userManager.getUserById(userId);
		mav.addObject("myProfileUser", user);
		
		return mav;
	}
	
	@RequestMapping(value="/usermap.html")
	public ModelAndView enterUserMap(HttpServletRequest request) throws SQLException{
		
		return new ModelAndView("usermap");
	}
	
	@RequestMapping(value="/admin.html")
	public ModelAndView enterAdmin(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<User> users = userManager.getAllUsers();
		
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("users", users);
	    
		return mav;
	}
}
