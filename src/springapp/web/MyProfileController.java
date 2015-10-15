package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.domain.User;
import springapp.service.PostManager;

@Controller
public class MyProfileController {
	
	@Autowired
	private PostManager postManager;
	
	@RequestMapping(value="/getUsersRecentPosts.html")
	public ModelAndView getUsersRecentPosts(HttpServletRequest request) throws SQLException, ParseException{
		
		User user =  (User) request.getSession().getAttribute("user");
		String id = user.getId();
		
		ArrayList<Post> posts = postManager.getUsersPostsByTimestamp(id);
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/getUsersStats.html")
	public ModelAndView getUsersStats(HttpServletRequest request) throws SQLException, ParseException{
		
		User user =  (User) request.getSession().getAttribute("user");
		String id = user.getId();
		
		String noOfPosts = postManager.getNoOfPostsByUser(id);
		int totalLikes = postManager.getTotalLikes(id);
		int totalDislikes = postManager.getTotalDislikes(id);
		
		ModelAndView mav = new ModelAndView("stats");
		mav.addObject("noOfPosts", noOfPosts);
		mav.addObject("totalLikes", totalLikes);
		mav.addObject("totalDislikes", totalDislikes);
		
		return mav;
	}
}
