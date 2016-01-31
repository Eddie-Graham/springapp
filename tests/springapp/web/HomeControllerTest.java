package springapp.web;

import org.junit.Test;
import springapp.BaseContextConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest extends BaseContextConfig{

	@Test
	public void postsByTimestamp() throws Exception{

		String url = "/postsByTimestamp.html";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void postsByNoOfReplies() throws Exception{

		String url = "/postsByNoOfReplies.html";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void postsByLikes() throws Exception{

		String url = "/postsByLikes.html";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void postsByDislikes() throws Exception{

		String url = "/postsByDislikes.html";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void postsByTotal() throws Exception{

		String url = "/postsByTotal.html";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void postsByTag() throws Exception{

		String url = "/postsByTag.html?tag=tagTest";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void submitPost() throws Exception{

		String url = "/submitPost.html?postText=postTextTest";

		mockMvc.perform(post(url)
				.session(mockSession))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void incrementLikes() throws Exception{

		String url = "/incrementLikes.html?postId=1";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void decrementDisikes() throws Exception{

		String url = "/decrementDisikes.html?postId=1";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void getPostComments() throws Exception{

		String url = "/getPostComments.html?postId=1";

		mockMvc.perform(get(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}

	@Test
	public void submitPostComment() throws Exception{

		String url = "/submitPostComment.html?postId=1&postText=postTextTest";

		mockMvc.perform(post(url)
				.session(mockSession))
				.andExpect(status().isOk());
	}
}
