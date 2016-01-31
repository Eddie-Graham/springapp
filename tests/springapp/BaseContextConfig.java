package springapp;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springapp.domain.User;
import springapp.service.UserManagerInterface;

import java.sql.SQLException;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/egraham/intelliJ/springapp/WebContent/WEB-INF/springapp-servlet.xml")
public class BaseContextConfig{

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	protected UserManagerInterface userManager;

	protected MockMvc mockMvc;
	protected MockHttpSession mockSession;

	@Before
	public void setup() throws SQLException, ParseException{

		// Jenkins push test 2

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		mockSession = new MockHttpSession(wac.getServletContext());

		User user = userManager.getUserByUsername("test");
		mockSession.setAttribute("userSesh", user);
	}
}
