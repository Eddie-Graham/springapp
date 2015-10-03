package springapp.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.service.DbService;

@Controller
public class HomePageController {
	
	@Autowired
	private DbService dbService;

	@RequestMapping(value="/homePage.html")
	public ModelAndView enterHomePage(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:loginPage.html");

		return new ModelAndView("homePage");
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		return "redirect:loginPage.html";
	}
	
	@RequestMapping(value="/postsByTimestamp.html")
	public ModelAndView postsByTimestamp(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = dbService.getPostsByTimestamp();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/postsByLikes.html")
	public ModelAndView postsByLikes(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = dbService.getPostsByLikes();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/submitPost.html")
	public String submitPost(HttpServletRequest request) throws SQLException, ParseException{
		
		String postText = request.getParameter("postText");
		String username = (String) request.getSession().getAttribute("loggedInUser");
		
		dbService.submitPost(postText, username);
		
		return "redirect:postsByTimestamp.html";
	}
	
	@RequestMapping(value="/incrementLikes.html")
	public @ResponseBody String incrementLikes(HttpServletRequest request) throws SQLException{
		
		String userId = (String) request.getSession().getAttribute("loggedInUserId");
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = dbService.getLikeRecord(userId, postId);
		// if entry exists in table
		if(rsLR.next())
			return "FAILED_LIKED";
		
		ResultSet rsDR = dbService.getDislikeRecord(userId, postId);
		// if entry exists in table
		if(rsDR.next())
			return "FAILED_DISLIKED";
		
		dbService.incrementLikes(postId);
		
		dbService.createLikeRecord(userId, postId);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/decrementLikes.html")
	public @ResponseBody String decrementLikes(HttpServletRequest request) throws SQLException{
		
		String userId = (String) request.getSession().getAttribute("loggedInUserId");
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = dbService.getLikeRecord(userId, postId);
		// if entry exists in table
		if(rsLR.next())
			return "FAILED_LIKED";

		ResultSet rsDR = dbService.getDislikeRecord(userId, postId);
		// if entry exists in table
		if(rsDR.next())
			return "FAILED_DISLIKED";
		
		dbService.decrementLikes(postId);
		
		dbService.createDislikeRecord(userId, postId);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/aboutPage.html")
	public ModelAndView enterAboutPage(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:loginPage.html");

		return new ModelAndView("aboutPage");
	}
	
}
