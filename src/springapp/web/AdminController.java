package springapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springapp.service.PostManagerInterface;
import springapp.service.UserManagerInterface;

@Controller
public class AdminController{

	@Autowired
	private PostManagerInterface postManager;

	@Autowired
	private UserManagerInterface userManager;

	@RequestMapping(value = "/deletePost.html")
	public @ResponseBody String deletePost(HttpServletRequest request){

		String postId = request.getParameter("postId");
		boolean fromPostComments = Boolean.valueOf(request.getParameter("fromPostComments"));

		postManager.deletePost(postId, fromPostComments);

		return "SUCCESS";
	}

	@RequestMapping(value = "/updateUser.html")
	public String updateUser(HttpServletRequest request){

		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String authority = request.getParameter("authority");
		String enabled = request.getParameter("enabled");
		String password = request.getParameter("password");

		userManager.updateUser(userId, username, email, authority, enabled, password);

		return "redirect:admin.html";
	}
}
