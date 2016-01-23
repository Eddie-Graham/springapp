package springapp.web;

import org.junit.Before;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/egraham/workspace/intelliJ/springapp/WebContent/WEB-INF/springapp-servlet.xml")
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private UserManagerInterface userManager;

	private MockMvc mockMvc;
	private MockHttpSession mockSession;

	@Before
	public void setup() throws SQLException, ParseException{

		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		mockSession = new MockHttpSession(wac.getServletContext());

		User user = userManager.getUserByUsername("test");
		mockSession.setAttribute("userSesh", user);
	}

	@Test
	public void postsByTimestamp() throws Exception {

		mockMvc.perform(get("/postsByTimestamp.html")
				.session(mockSession))
				.andExpect(status().isOk());
	}
}
