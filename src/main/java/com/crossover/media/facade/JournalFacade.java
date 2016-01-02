package com.crossover.media.facade;

import java.util.Collection;

import com.crossover.media.resource.JournalResource;

public interface JournalFacade {

	Collection<JournalResource> getAllJournals();

	JournalResource registerNewJournal(JournalResource journal);

	void deleteJournal(Long id);

	JournalResource getJournal(Long id);
}
