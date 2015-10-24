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

import springapp.domain.Post;
import springapp.domain.User;
import springapp.service.PostManager;
import springapp.service.UserManager;

@Controller
public class MyProfileController {
	
	@Autowired
	private PostManager postManager;
	
	@Autowired
	private UserManager userManager;
	
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

		User user =  (User) request.getSession().getAttribute("user");
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
		
		return "redirect:myprofile.html";
	}
	
	@RequestMapping(value="/delete.html")
	public String deleteProfileImage(HttpServletRequest request) {

		User user =  (User) request.getSession().getAttribute("user");
		String id = user.getId();
		
		String path = System.getenv("APP_ROOT") + "/profile_images/" + id + ".png";
		
		File f = new File(path);
		boolean success = f.delete();
		
		userManager.setHasProfilePic(false, id);
		// change object in session
		user.setHasProfilePic(false);
		
		System.out.println("Deleted file (" + success + "): " + path);
		
		return "redirect:myprofile.html";
	}
}
