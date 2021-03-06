package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.UserManagerInterface;

@Controller
public class LoginController{

	@Autowired
	private UserManagerInterface userManager;

	@RequestMapping(value = "/login.html")
	public ModelAndView enterLogin(HttpServletRequest request){

		User user = (User) request.getSession().getAttribute("userSesh");

		if(user != null)
			return new ModelAndView("redirect:home.html");

		return new ModelAndView("login");
	}

	@RequestMapping(value = "/loginSuccess.html")
	public @ResponseBody String loginSuccess(HttpServletRequest request) throws SQLException, ParseException{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = userManager.getUserByUsername(username);

		////////////////////////////////
		// set user object in session //
		////////////////////////////////
		request.getSession().setAttribute("userSesh", user);

		return "SUCCESS";
	}

	@RequestMapping(value = "/loginFail.html")
	public @ResponseBody String loginFail(HttpServletRequest request){

		return "FAILED";
	}

	@RequestMapping(value = "/checkUniqueEmail.html")
	public @ResponseBody String checkUniqueEmail(HttpServletRequest request) throws SQLException, ParseException{

		String email = request.getParameter("email");

		email = email.trim();

		User user = userManager.getUserByEmail(email);

		if(user == null)
			return "UNIQUE";

		return "NOT UNIQUE";
	}

	@RequestMapping(value = "/checkUniqueUsername.html")
	public @ResponseBody String checkUniqueUsername(HttpServletRequest request) throws SQLException, ParseException{

		String username = request.getParameter("username");

		username = username.trim();

		User user = userManager.getUserByUsername(username);

		if(user == null)
			return "UNIQUE";

		return "NOT UNIQUE";
	}

	@RequestMapping(value = "/submitRegistration.html")
	public @ResponseBody String submitRegistration(HttpServletRequest request) throws SQLException{

		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		//String confirmPassword = (String) request.getParameter("confirmPassword");

		username = username.trim();
		email = email.trim();
		password = password.trim();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		User user = new User(username, email, hashedPassword);

		userManager.createUser(user);

		System.out.println("User with username " + user.getUsername() + " registered");

		return "SUCCESS";
	}
}
