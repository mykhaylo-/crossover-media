package com.crossover.media.facade;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crossover.media.converter.Converter;
import com.crossover.media.model.Journal;
import com.crossover.media.resource.JournalResource;
import com.crossover.media.service.JournalService;

@Component
public class DefaultJournalFacade implements JournalFacade {

	@Resource(name = "journalService")
	private JournalService journalService;

	@Resource(name = "journalConverter")
	private Converter<Journal, JournalResource> journalConverter;

	@Resource(name = "journalResourceConverter")
	private Converter<JournalResource, Journal> journalResourceConverter;

	@Override
	public Collection<JournalResource> getAllJournals() {
		return StreamSupport.stream(journalService.getAllJournals().spliterator(), false)
				.map(journal -> journalConverter.convert(journal)).collect(Collectors.toList());
	};

	@Override
	public JournalResource registerNewJournal(JournalResource journalResource) {
		Journal journal = journalResourceConverter.convert(journalResource);
		Journal createdJournal = journalService.createNewJournal(journal);

		return journalConverter.convert(createdJournal);
	}

	@Override
	public void deleteJournal(Long id) {
		journalService.deleteJournal(id);
	}

	@Override
	public JournalResource getJournal(Long id) {
		Journal journal = journalService.getJournal(id);
		return journalConverter.convert(journal);
	}

}

