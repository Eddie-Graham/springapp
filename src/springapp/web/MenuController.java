package springapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		return "redirect:loginPage.html";
	}
	
	@RequestMapping(value="/aboutPage.html")
	public ModelAndView enterAboutPage(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:loginPage.html");

		return new ModelAndView("aboutPage");
	}
	
	@RequestMapping(value="/homePage.html")
	public ModelAndView enterHomePage(HttpServletRequest request){
	    
		if(request.getSession().getAttribute("loggedInUser") == null)
			return new ModelAndView("redirect:loginPage.html");

		return new ModelAndView("homePage");
	}

}
