package com.crossover.media.rest;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.media.JournalsApplication;
import com.crossover.media.config.SecurityConfig;
import com.crossover.media.config.WebMvcConfig;
import com.crossover.media.model.CurrentUser;
import com.crossover.media.model.Role;
import com.crossover.media.model.User;
import com.crossover.media.service.SubscriptionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JournalsApplication.class, SecurityConfig.class, WebMvcConfig.class })
@WebAppConfiguration
public class SubscriptionControllerTest {

	@InjectMocks
	private SubscriptionController unit;

	@Mock
	private SubscriptionService subscriptionService;

	// @Resource
	// private WebApplicationContext context;

	private MockMvc mockMvc;

	@Resource(name = "springSecurityFilterChain")
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(unit)
				.setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver())
				.addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void testSubscribe() throws Exception {
		User user = new User();
		user.setEmail("a@a.com");
		user.setPassword("some_pass");
		user.setId(4L);
		user.setRole(Role.USER);
		CurrentUser currentUser = new CurrentUser(user);
		mockMvc.perform(post("/journals/14/subscribers").with(user(currentUser))).andExpect(status().isOk())
				.andExpect(content().string(""));
		verify(subscriptionService).subscribe(14L, currentUser);
	}

	@Test
	public void testUnsubscribe() throws Exception {
		User user = new User();
		user.setEmail("a@a.com");
		user.setPassword("some_pass");
		user.setId(4L);
		user.setRole(Role.USER);

		CurrentUser userDetails = new CurrentUser(user);

		mockMvc.perform(delete("/journals/14/subscribers").with(user(userDetails))).andExpect(status().isOk())
				.andExpect(content().string(""));

		verify(subscriptionService).unsubscribe(14L, userDetails);
	}

	// TODO : delete this as unnecessary
	public static RequestPostProcessor rob() {
		return user("rob").roles("ADMIN");
	}
}
