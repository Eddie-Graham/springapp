package springapp.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.LikeDislikeRecordManagerInterface;
import springapp.service.PostManagerInterface;
import springapp.service.UserManagerInterface;

@Controller
public class MyProfileController {
	
	@Autowired
	private PostManagerInterface postManager;
	
	@Autowired
	private UserManagerInterface userManager;
	
	@Autowired
	private LikeDislikeRecordManagerInterface likeDislikeRecordManager;
	
	@RequestMapping(value="/getUsersStats.html")
	public ModelAndView getUsersStats(HttpServletRequest request) throws SQLException, ParseException{
		
		String id = request.getParameter("userId");	
		User user = userManager.getUserById(id);
		
		// main posts
		int noOfPosts = postManager.getNoOfPostsByUser(id, false);
		int totalLikes = postManager.getTotalLikes(id, false);
		int totalDislikes = postManager.getTotalDislikes(id, false);
		int totalPostsLiked = likeDislikeRecordManager.getTotalPostsLiked(id, false);
		int totalPostsDisliked = likeDislikeRecordManager.getTotalPostsDisliked(id, false);
		// replies
		int noOfReplies = postManager.getNoOfPostsByUser(id, true);
		int totalReplyLikes = postManager.getTotalLikes(id, true);
		int totalReplyDislikes = postManager.getTotalDislikes(id, true);
		int totalRepliesLiked = likeDislikeRecordManager.getTotalPostsLiked(id, true);
		int totalRepliesDisliked = likeDislikeRecordManager.getTotalPostsDisliked(id, true);
		
		// order stats are added is in conjunction with stats.jsp 
		ArrayList<Integer> stats = new ArrayList<Integer>();
		stats.add(noOfPosts);
		stats.add(totalLikes);
		stats.add(totalDislikes);
		stats.add(totalPostsLiked);
		stats.add(totalPostsDisliked);
		stats.add(noOfReplies);
		stats.add(totalReplyLikes);
		stats.add(totalReplyDislikes);
		stats.add(totalRepliesLiked);
		stats.add(totalRepliesDisliked);
	
		ModelAndView mav = new ModelAndView("common_components/stats");
		mav.addObject("stats", stats);
		mav.addObject("username", user.getUsername());
		
		return mav;
	}
	
	/**
	 * Uploads selected profile image into /profile_images/ folder using
	 * user's id as file name (eg 7.png).
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/upload.html", method = RequestMethod.POST)
	public String uploadProfileImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

		User user =  (User) request.getSession().getAttribute("userSesh");
		String id = user.getId();
		
		if (!file.isEmpty()) {
			
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			
			String path = System.getenv("APP_ROOT") + "/profile_images/" + id + ".png";
			
			File destination = new File(path); 

			ImageIO.write(bufferedImage, "png", destination);
			
			userManager.setHasProfilePic(true, id);
			// change object in session
			user.setHasProfilePic(true);
			
			System.out.println("User uploaded profile pic at path: " + path);
		}
		
		return "redirect:myprofile.html?id=" + id;
	}
	
	@RequestMapping(value="/delete.html")
	public String deleteProfileImage(HttpServletRequest request) {

		User user =  (User) request.getSession().getAttribute("userSesh");
		String id = user.getId();
		
		String path = System.getenv("APP_ROOT") + "/profile_images/" + id + ".png";
		
		File f = new File(path);
		boolean success = f.delete();
		
		userManager.setHasProfilePic(false, id);
		// change object in session
		user.setHasProfilePic(false);
		
		System.out.println("Deleted file (" + success + "): " + path);
		
		return "redirect:myprofile.html?id=" + id;
	}
}
