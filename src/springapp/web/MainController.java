package springapp.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.dbcon.DbConImpl;

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
		
		String query = "select * from users where email = '" + email + "';";			
	
		ResultSet rs = DbConImpl.makeConnectionAndRunQuery(query);
		
		String username = "";
		String passwordStr = "";
		
		while (rs.next()) {
			username = rs.getString("username");
            passwordStr = rs.getString("password");
            break;
        }
		
		ModelAndView mav = new ModelAndView("main");
		
		if(username.isEmpty() || !password.equals(passwordStr)){
			return mav;
		}
		
		System.out.println("Logged in as : " + username);
		
		request.getSession().setAttribute("loggedInUser", username);
	    
	    return mav;
	}
	
	@RequestMapping(value="/logout.html")
	public ModelAndView logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		ModelAndView mav = new ModelAndView("main");
		
	    return mav;
	}

}
