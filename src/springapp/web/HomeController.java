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
import springapp.domain.User;
import springapp.service.LikeDislikeRecordManager;
import springapp.service.PostManager;

@Controller
public class HomeController {
	
	@Autowired
	private PostManager postManager;
	
	@Autowired
	private LikeDislikeRecordManager likeDislikeRecordManager;
	
	@RequestMapping(value="/postsByTimestamp.html")
	public ModelAndView postsByTimestamp(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = postManager.getPostsByTimestamp();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/postsByLikes.html")
	public ModelAndView postsByLikes(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = postManager.getPostsByLikes();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/postsByDislikes.html")
	public ModelAndView postsByDislikes(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = postManager.getPostsByDislikes();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/postsByTotal.html")
	public ModelAndView postsByTotal(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = postManager.getPostsByTotal();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
		return mav;
	}
	
	@RequestMapping(value="/submitPost.html")
	public String submitPost(HttpServletRequest request) throws SQLException, ParseException{
		
		User user =  (User) request.getSession().getAttribute("user");
		String id = user.getId();
		
		String postText = request.getParameter("postText");
		
		postManager.submitPost(postText, id);
		
		return "redirect:postsByTimestamp.html";
	}
	
	@RequestMapping(value="/incrementLikes.html")
	public @ResponseBody String incrementLikes(HttpServletRequest request) throws SQLException{
		
		User user =  (User) request.getSession().getAttribute("user");
		String userId = user.getId();
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = likeDislikeRecordManager.getLikeRecord(userId, postId);
		// already liked
		if(rsLR.next()){
			likeDislikeRecordManager.removeLikeRecord(userId, postId);
			likeDislikeRecordManager.decrementLikes(postId);
			return "UNDO_LIKED";
		}		
		
		ResultSet rsDR = likeDislikeRecordManager.getDislikeRecord(userId, postId);
		// already disliked
		if(rsDR.next()){
			likeDislikeRecordManager.removeDislikeRecord(userId, postId);
			likeDislikeRecordManager.incrementDislikes(postId);
			likeDislikeRecordManager.incrementLikes(postId);
			likeDislikeRecordManager.createLikeRecord(userId, postId);
			return "REVERSED_DISLIKE";
		}
		
		likeDislikeRecordManager.incrementLikes(postId);
		
		likeDislikeRecordManager.createLikeRecord(userId, postId);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value="/decrementDisikes.html")
	public @ResponseBody String decrementDisikes(HttpServletRequest request) throws SQLException{
	    
		User user =  (User) request.getSession().getAttribute("user");
		String userId = user.getId();
		String postId = request.getParameter("postId");
		
		ResultSet rsLR = likeDislikeRecordManager.getLikeRecord(userId, postId);
		// already liked
		if(rsLR.next()){
			likeDislikeRecordManager.removeLikeRecord(userId, postId);
			likeDislikeRecordManager.decrementLikes(postId);
			likeDislikeRecordManager.decrementDislikes(postId);
			likeDislikeRecordManager.createDislikeRecord(userId, postId);
			return "REVERSED_LIKE";
		}
			
		ResultSet rsDR = likeDislikeRecordManager.getDislikeRecord(userId, postId);
		// already disliked
		if(rsDR.next()){
			likeDislikeRecordManager.removeDislikeRecord(userId, postId);
			likeDislikeRecordManager.incrementDislikes(postId);
			return "UNDO_DISLIKED";
		}
		
		likeDislikeRecordManager.decrementDislikes(postId);
		
		likeDislikeRecordManager.createDislikeRecord(userId, postId);
		
		return "SUCCESS";
	}
}
