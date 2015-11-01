package springapp.web;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import springapp.domain.User;
import springapp.service.UserManager;

@Controller
public class UserMapController {
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/getUsers.html")
	public @ResponseBody String getUsers(HttpServletRequest request) throws SQLException {
		
		ArrayList<User> users = userManager.getAllUsersWithLatLong();
		
		Gson gson = new Gson();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json = gson.toJson(users);
		
		return json;
	}
	
	@RequestMapping(value="/postLatLong.html", method = RequestMethod.POST)
	public @ResponseBody String postLatLong(HttpServletRequest request) throws SQLException {
		
		User user =  (User) request.getSession().getAttribute("user");
		String id = user.getId();
		
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		userManager.setLatLong(id, latitude, longitude);
		
		// update session object
		user.setLatitude(Double.valueOf(latitude));
		user.setLongitude(Double.valueOf(longitude));
		
		Gson gson = new Gson();
		String json = gson.toJson(user);
		
		return json;
	}
}
