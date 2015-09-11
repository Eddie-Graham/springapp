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
	public ModelAndView enterView(HttpServletRequest request){
	    
		ModelAndView mav = new ModelAndView("main");
		
	    return mav;
	}

	@RequestMapping(value="/login.html")
	public ModelAndView login(HttpServletRequest request) throws SQLException{
		
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		User user = DbService.getUserByEmail(email);
		
		ModelAndView mav = new ModelAndView("main");
		
		if(user == null || !password.equals(user.getPassword())){
			return mav;
		}
		
		request.getSession().setAttribute("loggedInUser", user.getUsername());
		
		System.out.println("Logged in as : " + user.getUsername());
	    
	    return mav;
	}
	
	@RequestMapping(value="/logout.html")
	public ModelAndView logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		ModelAndView mav = new ModelAndView("main");
		
	    return mav;
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
	
	@RequestMapping(value="/submitRegister.html")
	public ModelAndView submitRegister(HttpServletRequest request) throws SQLException{
		
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		//String confirmPassword = (String) request.getParameter("confirmPassword");
		
		ModelAndView mav = new ModelAndView("main");
		
		User user = new User(username, email, password);
		
		DbService.createUser(user);

		request.getSession().setAttribute("loggedInUser", user.getUsername());
		
		System.out.println("User with username " + user.getUsername() + " registered");
		
	    return mav;
	}
}
