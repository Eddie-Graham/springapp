package springapp.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.domain.User;
import springapp.service.LikeDislikeRecordManagerInterface;
import springapp.service.PostCommentsManagerInterface;
import springapp.service.PostManagerInterface;
import springapp.service.TagManagerInterface;

@Controller
public class HomeController{

	@Autowired
	private PostManagerInterface postManager;

	@Autowired
	private TagManagerInterface tagManager;

	@Autowired
	private LikeDislikeRecordManagerInterface likeDislikeRecordManager;

	@Autowired
	private PostCommentsManagerInterface postCommentsManager;

	@RequestMapping(value = "/postsByTimestamp.html")
	public ModelAndView postsByTimestamp(HttpServletRequest request) throws SQLException, ParseException{

		String userId = request.getParameter("userId");

		ArrayList<Post> posts = postManager.getPostsByTimestamp(userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/postsByNoOfReplies.html")
	public ModelAndView postsByNoOfReplies(HttpServletRequest request) throws SQLException, ParseException{

		String userId = request.getParameter("userId");

		ArrayList<Post> posts = postManager.getPostsByNoOfReplies(userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/postsByLikes.html")
	public ModelAndView postsByLikes(HttpServletRequest request) throws SQLException, ParseException{

		String userId = request.getParameter("userId");

		ArrayList<Post> posts = postManager.getPostsByLikes(userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/postsByDislikes.html")
	public ModelAndView postsByDislikes(HttpServletRequest request) throws SQLException, ParseException{

		String userId = request.getParameter("userId");

		ArrayList<Post> posts = postManager.getPostsByDislikes(userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/postsByTotal.html")
	public ModelAndView postsByTotal(HttpServletRequest request) throws SQLException, ParseException{

		String userId = request.getParameter("userId");

		ArrayList<Post> posts = postManager.getPostsByTotal(userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/postsByTag.html")
	public ModelAndView postsByTag(HttpServletRequest request) throws SQLException, ParseException{

		String tag = request.getParameter("tag");
		String userId = request.getParameter("userId"); //TODO

		ArrayList<Post> posts = postManager.getPostsByTag(tag, userId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("userId", userId);

		return mav;
	}

	@RequestMapping(value = "/submitPost.html", method = RequestMethod.POST)
	public String submitPost(HttpServletRequest request) throws SQLException, ParseException{

		User user = (User) request.getSession().getAttribute("userSesh");
		String id = user.getId();

		String postText = request.getParameter("postText");

		String postId = postManager.submitPost(postText, id);

		// Find any tags in post
		ArrayList<String> tags = new ArrayList<String>();
		String[] splitPostText = postText.split(" ");

		for(String token : splitPostText){

			if(token.startsWith("#"))
				tags.add(token);
		}

		// Insert tags into database
		for(String tag : tags)
			tagManager.insertTag(tag, postId);

		return "redirect:postsByTimestamp.html";
	}

	@RequestMapping(value = "/incrementLikes.html")
	public @ResponseBody String incrementLikes(HttpServletRequest request) throws SQLException{

		User user = (User) request.getSession().getAttribute("userSesh");
		String userId = user.getId();
		String postId = request.getParameter("postId");
		boolean fromPostComments = Boolean.valueOf(request.getParameter("fromPostComments"));

		ResultSet rsLR = likeDislikeRecordManager.getLikeRecord(userId, postId, fromPostComments);
		// already liked
		if(rsLR.next()){
			likeDislikeRecordManager.removeLikeRecord(userId, postId, fromPostComments);
			postManager.decrementLikes(postId, fromPostComments);
			return "UNDO_LIKED";
		}

		ResultSet rsDR = likeDislikeRecordManager.getDislikeRecord(userId, postId, fromPostComments);
		// already disliked
		if(rsDR.next()){
			likeDislikeRecordManager.removeDislikeRecord(userId, postId, fromPostComments);
			postManager.incrementDislikes(postId, fromPostComments);
			postManager.incrementLikes(postId, fromPostComments);
			likeDislikeRecordManager.createLikeRecord(userId, postId, fromPostComments);
			return "REVERSED_DISLIKE";
		}

		postManager.incrementLikes(postId, fromPostComments);

		likeDislikeRecordManager.createLikeRecord(userId, postId, fromPostComments);

		return "SUCCESS";
	}

	@RequestMapping(value = "/decrementDisikes.html")
	public @ResponseBody String decrementDisikes(HttpServletRequest request) throws SQLException{

		User user = (User) request.getSession().getAttribute("userSesh");
		String userId = user.getId();
		String postId = request.getParameter("postId");
		boolean fromPostComments = Boolean.valueOf(request.getParameter("fromPostComments"));

		ResultSet rsLR = likeDislikeRecordManager.getLikeRecord(userId, postId, fromPostComments);
		// already liked
		if(rsLR.next()){
			likeDislikeRecordManager.removeLikeRecord(userId, postId, fromPostComments);
			postManager.decrementLikes(postId, fromPostComments);
			postManager.decrementDislikes(postId, fromPostComments);
			likeDislikeRecordManager.createDislikeRecord(userId, postId, fromPostComments);
			return "REVERSED_LIKE";
		}

		ResultSet rsDR = likeDislikeRecordManager.getDislikeRecord(userId, postId, fromPostComments);
		// already disliked
		if(rsDR.next()){
			likeDislikeRecordManager.removeDislikeRecord(userId, postId, fromPostComments);
			postManager.incrementDislikes(postId, fromPostComments);
			return "UNDO_DISLIKED";
		}

		postManager.decrementDislikes(postId, fromPostComments);

		likeDislikeRecordManager.createDislikeRecord(userId, postId, fromPostComments);

		return "SUCCESS";
	}

	@RequestMapping(value = "/getPostComments.html")
	public ModelAndView getPostComments(HttpServletRequest request) throws SQLException, NumberFormatException,
			ParseException{

		String postId = request.getParameter("postId");
		String backgroundColor = request.getParameter("backgroundColor");

		ArrayList<Post> posts = postCommentsManager.getPostComments(postId);

		ModelAndView mav = new ModelAndView("common_components/posts");
		mav.addObject("posts", posts);
		mav.addObject("fromPostComments", true);
		mav.addObject("postCommentsColor", backgroundColor);

		return mav;
	}

	@RequestMapping(value = "/submitPostComment.html", method = RequestMethod.POST)
	public @ResponseBody String submitPostComment(HttpServletRequest request) throws SQLException, ParseException{

		User user = (User) request.getSession().getAttribute("userSesh");
		String id = user.getId();

		String postText = request.getParameter("postText");
		String postId = request.getParameter("postId");

		String postIndex = postCommentsManager.getIncrementedPostIndex(postId);

		postCommentsManager.submitPostComment(postText, id, postId, postIndex);

		return "SUCCESS";
	}
}
