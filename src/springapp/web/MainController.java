package springapp.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springapp.dbcon.DbCon;
import springapp.dbcon.DbConImpl;

@Controller
public class MainController {
	
	@RequestMapping(value="/main.html")
	public ModelAndView enterView(){
	    
		ModelAndView mav = new ModelAndView("main");
		
		
	    
	    return mav;
	}

	@RequestMapping(value="/login.html")
	public ModelAndView login(HttpServletRequest request) throws SQLException{
		
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
		
		if(!password.equals(passwordStr)){
			
		}
		
		request.getSession().setAttribute("LoggedInUser", username);
	    
		ModelAndView mav = new ModelAndView("main");
		
		mav.addObject("LoggedInUser", username);
	    
	    return mav;
	}

}
