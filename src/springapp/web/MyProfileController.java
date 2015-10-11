package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.service.DbService;

@Controller
public class MyProfileController {

	@Autowired
	private DbService dbService;
	
	@RequestMapping(value="/getUsersRecentPosts.html")
	public ModelAndView getUsersRecentPosts(HttpServletRequest request) throws SQLException, ParseException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		ArrayList<Post> posts = dbService.getUsersPostsByTimestamp(username);
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/getUsersStats.html")
	public ModelAndView getUsersStats(HttpServletRequest request) throws SQLException, ParseException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		ArrayList<Post> posts = dbService.getUsersPostsByTimestamp(username);
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
}
