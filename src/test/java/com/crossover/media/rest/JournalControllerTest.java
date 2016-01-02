package com.crossover.media.rest;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crossover.media.facade.JournalFacade;
import com.crossover.media.model.Journal;
import com.crossover.media.resource.IssueResource;
import com.crossover.media.resource.JournalResource;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class JournalControllerTest {

	@InjectMocks
	private JournalController unit;

	@Mock
	private JournalFacade journalFacade;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
	}

	@Test
	public void testJournalRegistration() throws Exception {
		Journal journal = new Journal();
		journal.setName("some journal");
		journal.setDescription("some description");

		String postData = objectMapper.writeValueAsString(journal);

		mockMvc.perform(post("/journals").content(postData).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().string(""));

		JournalResource journalResource = new JournalResource();
		journalResource.setDescription("some description");
		journalResource.setName("some journal");
		verify(journalFacade).registerNewJournal(journalResource);
	}

	@Test
	public void testShowJournal() throws Exception {
		JournalResource journal = new JournalResource();
		journal.setName("some journal");
		journal.setDescription("some description");
		journal.setId(82L);

		IssueResource issue1 = new IssueResource();
		issue1.setFileGuid("aaaaa");
		issue1.setId(4L);
		issue1.setName("January 2016");

		IssueResource issue2 = new IssueResource();
		issue1.setFileGuid("bbbbbbb");
		issue1.setId(45L);
		issue1.setName("February 2016");

		Set<IssueResource> issues = new HashSet<>();
		issues.add(issue1);
		issues.add(issue2);

		journal.setIssues(issues);

		when(journalFacade.getJournal(anyLong())).thenReturn(journal);

		String expectedJournalJson = objectMapper.writeValueAsString(journal);

		mockMvc.perform(get("/journals/82")).andExpect(status().isOk())
				.andExpect(content().string(expectedJournalJson));

		verify(journalFacade).getJournal(82L);
	}

	@Test
	public void testShowJournalWhenJournalDoesntExist() throws Exception {
		when(journalFacade.getJournal(anyLong())).thenReturn(null);

		mockMvc.perform(get("/journals/82")).andExpect(status().isNotFound()).andExpect(content().string(""));

		verify(journalFacade).getJournal(82L);
	}

	@Test
	public void testGetAllJournals() throws Exception {
		JournalResource journal1 = new JournalResource();
		journal1.setName("some journal1");
		journal1.setDescription("some description1");
		journal1.setId(824L);

		JournalResource journal2 = new JournalResource();
		journal2.setName("some journal2");
		journal2.setDescription("some description2");
		journal2.setId(82L);

		List<JournalResource> journals = Arrays.asList(journal1, journal2);
		when(journalFacade.getAllJournals()).thenReturn(journals);

		String expectedJournalsJson = objectMapper.writeValueAsString(journals);
		mockMvc.perform(get("/journals")).andExpect(status().isOk()).andExpect(content().string(expectedJournalsJson));

		verify(journalFacade).getAllJournals();
	}

	@Test
	public void testDeleteJournal() throws Exception {

		mockMvc.perform(delete("/journals/78")).andExpect(status().isNoContent()).andExpect(content().string(""));

		verify(journalFacade).deleteJournal(78L);

	}
}
