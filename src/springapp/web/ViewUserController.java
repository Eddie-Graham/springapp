package springapp.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewUserController {

	@RequestMapping(value="/viewuser.html")
	public ModelAndView enterViewUser(HttpServletRequest request) throws SQLException{
	    
		return new ModelAndView("viewuser");
	}
}
