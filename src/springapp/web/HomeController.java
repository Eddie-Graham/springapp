package springapp.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.service.DbService;

@Controller
public class HomeController {
	
	@Autowired
	private DbService dbService;
	
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
		String postText = request.getParameter("postText");
		
		dbService.submitPost(postText, username);
		
		return "redirect:postsByTimestamp.html";
	}
	
	@RequestMapping(value="/incrementLikes.html")
	public @ResponseBody String incrementLikes(HttpServletRequest request) throws SQLException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    
	    String userId = dbService.getUserId(username);
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = dbService.getLikeRecord(userId, postId);
		// already liked
		if(rsLR.next()){
			dbService.removeLikeRecord(userId, postId);
			dbService.decrementLikes(postId);
			return "UNDO_LIKED";
		}
		
		ResultSet rsDR = dbService.getDislikeRecord(userId, postId);
		// already disliked
		if(rsDR.next()){
			dbService.removeDislikeRecord(userId, postId);
			dbService.incrementDislikes(postId);
			dbService.incrementLikes(postId);
			dbService.createLikeRecord(userId, postId);
			return "REVERSED_DISLIKE";
		}
		
		dbService.incrementLikes(postId);
		
		dbService.createLikeRecord(userId, postId);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/decrementDisikes.html")
	public @ResponseBody String decrementDisikes(HttpServletRequest request) throws SQLException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    
	    String userId = dbService.getUserId(username);
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = dbService.getLikeRecord(userId, postId);
		// already liked
		if(rsLR.next()){
			dbService.removeLikeRecord(userId, postId);
			dbService.decrementLikes(postId);
			dbService.decrementDislikes(postId);
			dbService.createDislikeRecord(userId, postId);
			return "REVERSED_LIKE";
		}
			
		ResultSet rsDR = dbService.getDislikeRecord(userId, postId);
		// already disliked
		if(rsDR.next()){
			dbService.removeDislikeRecord(userId, postId);
			dbService.incrementDislikes(postId);
			return "UNDO_DISLIKED";
		}
		
		dbService.decrementDislikes(postId);
		
		dbService.createDislikeRecord(userId, postId);
		
		return "SUCCESS";
	}
}
