package com.revature;

import static org.hamcrest.CoreMatchers.is;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
>>>>>>> 643c4cf... Service and controller tests are passing with mock users added
=======
>>>>>>> 400c48f... Fix kafka test inteference issue.
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Answer;
import com.revature.models.User;
import com.revature.services.AnswerService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AnswerServiceApplication.class)
@AutoConfigureMockMvc
public class AnswerControllerTest {

	static User u1;
<<<<<<< HEAD
	@Autowired
	private ObjectMapper mapper;
<<<<<<< HEAD

=======
	
>>>>>>> edd15a5... Added application.properties for testing to disable Consul
=======

	@Autowired
	private ObjectMapper mapper;

>>>>>>> 400c48f... Fix kafka test inteference issue.
	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private AnswerService answerService;
<<<<<<< HEAD
<<<<<<< HEAD

	@Before
=======

<<<<<<< HEAD
	@BeforeEach
>>>>>>> 400c48f... Fix kafka test inteference issue.
=======
	@Before
>>>>>>> 9262c47... Fixed test issues.
	public void setUp() {
		u1 = new User(12, 26, 0, true, null, "admin@rss.com", "Admin", "Admin");
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	/** @author ken */
<<<<<<< HEAD
=======
	
	@Before
	public void setUp() {  
       u1 = new User(12,26,0,true,null,"admin@rss.com","Admin","Admin");
   	   mvc = MockMvcBuilders
   				.webAppContextSetup(context)
   				.apply(springSecurity())
   				.build();
    }
	
	/**@author ken*/
>>>>>>> f913869... Changed @Before to @BeforeEach in tests
	@Test
<<<<<<< HEAD
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
	public void testGetAnswers() throws Exception {
=======
	@WithMockUser(username="user@rss.com", password="12345", authorities="USER")
	public void testGetAnswers() throws Exception{
>>>>>>> edd15a5... Added application.properties for testing to disable Consul
=======
	@Test
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
	public void testGetAnswers() throws Exception {
>>>>>>> 400c48f... Fix kafka test inteference issue.
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(1, 1, 1, "Test content", LocalDateTime.MIN, LocalDateTime.MIN));
		Page<Answer> pageResult = new PageImpl<>(answers);

		when(answerService.getAnswers(Mockito.any(Pageable.class))).thenReturn(pageResult);

		mvc.perform(get("/answer").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content[0].id", is(1)));
	}

	/** @author Natasha Poser */
	@Test
<<<<<<< HEAD
<<<<<<< HEAD
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
=======
	@WithMockUser(username = "user@rss.com", password = "12345",authorities="USER")
>>>>>>> edd15a5... Added application.properties for testing to disable Consul
=======
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
>>>>>>> 400c48f... Fix kafka test inteference issue.
	public void testGetAnswerByQuestionId() throws Exception {
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(1, 1, 1, "Test content", LocalDateTime.MIN, LocalDateTime.MIN));
		Page<Answer> pageResult = new PageImpl<>(answers);

		when(answerService.getAnswerByQuestionId(Mockito.any(Pageable.class), Mockito.anyInt())).thenReturn(pageResult);

		mvc.perform(get("/answer/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content[0].id", is(1)));
	}

	/** @author ken *//*
						 * @Test
						 * 
						 * @WithMockUser(username = "user@rss.com", password = "12345", authorities =
						 * "USER") public void testSaveAnswer() throws Exception { Answer answer = new
						 * Answer(1, 1, 1, "test content", LocalDateTime.MIN, LocalDateTime.MIN);
						 * 
						 * when(answerService.save(Mockito.any(Answer.class))).thenReturn(answer);
						 * 
						 * String toUpdate = mapper.writeValueAsString(answer); MvcResult result =
						 * mvc.perform(MockMvcRequestBuilders.post("/answer")
						 * .contentType(MediaType.APPLICATION_JSON) .content(toUpdate)
						 * .accept(MediaType.APPLICATION_JSON) ).andReturn(); String content =
						 * result.getResponse().getContentAsString(); System.out.println("result = " +
						 * content); assertEquals(200, result.getResponse().getStatus());
						 * assertTrue("This return object conains the string",
						 * content.contains("test content")); assertNotEquals(null, content);
						 * 
						 * }
						 */

	/** @author ken */
	@Test
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
	public void testGetAnswerByUserId() throws Exception {
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(1, 1, 1, "Test content", LocalDateTime.MIN, LocalDateTime.MIN));
		Page<Answer> pageResult = new PageImpl<>(answers);

		when(answerService.getAllAnswersByUserID(Mockito.any(Pageable.class), Mockito.anyInt())).thenReturn(pageResult);

		mvc.perform(get("/answer/user/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content[0].id", is(1)));
	}

	/** @author Natasha Poser */
	@Test
	@WithMockUser(username = "user@rss.com", password = "12345", authorities = "USER")
	public void testGetAnswerById() throws Exception {
		Answer answer = new Answer(1, 1, 1, "Test content", LocalDateTime.MIN, LocalDateTime.MIN);

		when(answerService.getAnswerById(Mockito.anyInt())).thenReturn(answer);

		mvc.perform(get("/answer/id/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

}
