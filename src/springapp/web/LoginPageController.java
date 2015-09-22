package springapp.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.User;
import springapp.service.DbService;

@Controller
public class LoginPageController {

	@RequestMapping(value="/loginPage.html")
	public ModelAndView enterLoginPage(HttpServletRequest request){
		
		if(request.getSession().getAttribute("loggedInUser") != null)
			return new ModelAndView("redirect:dashboardPage.html");
		
		return new ModelAndView("loginPage");
	}
	
	@RequestMapping(value="/login.html")
	public @ResponseBody String login(HttpServletRequest request) throws SQLException{
		
		String email = (String) request.getParameter("loginEmail");
		String password = (String) request.getParameter("loginPassword");
		
		email = email.trim();
		password = password.trim();
		
		User user = DbService.getUserByEmail(email);
		
		if(user == null || !password.equals(user.getPassword())){
			return "FAILED";
		}
		
		request.getSession().setAttribute("loggedInUser", user.getUsername());
		
		System.out.println("Logged in as : " + user.getUsername());
	    
		return "SUCCESS";
	}
	
	@RequestMapping(value="/checkUniqueEmail.html")
	public @ResponseBody String checkUniqueEmail(HttpServletRequest request) throws SQLException{
		
		String email = request.getParameter("email");
		
		email = email.trim();
		
		User user = DbService.getUserByEmail(email);
		
		if(user == null)
			return "UNIQUE";
		
		return "NOT UNIQUE";
	}
	
	@RequestMapping(value="/checkUniqueUsername.html")
	public @ResponseBody String checkUniqueUsername(HttpServletRequest request) throws SQLException{
		
		String username = request.getParameter("username");
		
		username = username.trim();
		
		User user = DbService.getUserByUsername(username);
		
		if(user == null)
			return "UNIQUE";
		
		return "NOT UNIQUE";
	}
	
	@RequestMapping(value="/submitRegistration.html")
	public @ResponseBody String submitRegistration(HttpServletRequest request) throws SQLException{
		
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		//String confirmPassword = (String) request.getParameter("confirmPassword");
		
		username = username.trim();
		email = email.trim();
		password = password.trim();
		
		User user = new User(username, email, password);
		
		DbService.createUser(user);
		
		System.out.println("User with username " + user.getUsername() + " registered");
		
		return "SUCCESS";
	}
}
