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
public class MainController {
	
	@RequestMapping(value="/main.html")
	public ModelAndView enterMain(HttpServletRequest request){
	    
		ModelAndView mav = new ModelAndView("main");
		
	    return mav;
	}

	@RequestMapping(value="/login.html")
	public String login(HttpServletRequest request) throws SQLException{
		
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		User user = DbService.getUserByEmail(email);
		
		if(user == null || !password.equals(user.getPassword())){
			return "redirect:main.html";
		}
		
		request.getSession().setAttribute("loggedInUser", user.getUsername());
		
		System.out.println("Logged in as : " + user.getUsername());
	    
		return "redirect:main.html";
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		return "redirect:main.html";
	}
	
	@RequestMapping(value="/register.html")
	public ModelAndView register(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("register");
		
	    return mav;
	}
	
	@RequestMapping(value="/checkUniqueEmail.html")
	public @ResponseBody String checkUniqueEmail(HttpServletRequest request) throws SQLException{
		
		String email = request.getParameter("email");
		
		User user = DbService.getUserByEmail(email);
		
		if(user == null)
			return "UNIQUE";
		
	    return "NOT UNIQUE";
	}
	
	@RequestMapping(value="/checkUniqueUsername.html")
	public @ResponseBody String checkUniqueUsername(HttpServletRequest request) throws SQLException{
		
		String username = request.getParameter("username");
		
		User user = DbService.getUserByUsername(username);
		
		if(user == null)
			return "UNIQUE";
		
	    return "NOT UNIQUE";
	}
	
	@RequestMapping(value="/submitRegistration.html")
	public String submitRegister(HttpServletRequest request) throws SQLException{
		
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		//String confirmPassword = (String) request.getParameter("confirmPassword");
		
		User user = new User(username, email, password);
		
		DbService.createUser(user);

		request.getSession().setAttribute("loggedInUser", user.getUsername());
		
		System.out.println("User with username " + user.getUsername() + " registered");
		
		return "redirect:main.html";
	}
}
