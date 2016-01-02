package com.crossover.media.rest;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.media.exception.ResourceNotFoundException;
import com.crossover.media.facade.JournalFacade;
import com.crossover.media.resource.JournalResource;

@RestController
@RequestMapping("/journals")
public class JournalController {

	@Resource
	private JournalFacade journalFacade;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public JournalResource register(@RequestBody JournalResource journal) {
		return journalFacade.registerNewJournal(journal);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public JournalResource showJournal(@PathVariable("id") Long id) {
		JournalResource journal = journalFacade.getJournal(id);
		if (journal == null) {
			throw new ResourceNotFoundException();
		}
		return journal;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<JournalResource> listAll() {
		return journalFacade.getAllJournals();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		journalFacade.deleteJournal(id);
	}
}
