package springapp.web;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		System.out.println("Sesh: "+ request.getSession().getAttribute("LoggedInUser"));
		
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
	
	@RequestMapping(value="/submitRegister.html")
	public ModelAndView submitRegister(HttpServletRequest request) throws SQLException{
		
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String confirmPassword = (String) request.getParameter("confirmPassword");
		
		ModelAndView mav = new ModelAndView("main");
		
		if(!password.equals(confirmPassword))
			return mav;
		
		User user = new User(username, email, password);
		
		boolean successful = DbService.createUser(user);
		
		if(successful)
			request.getSession().setAttribute("loggedInUser", user.getUsername());
		
	    return mav;
	}
}
