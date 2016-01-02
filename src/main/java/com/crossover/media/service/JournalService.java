package com.crossover.media.service;

import com.crossover.media.model.Journal;

public interface JournalService {

	Iterable<Journal> getAllJournals();

	Journal createNewJournal(Journal journal);

	void deleteJournal(Long id);

	Journal getJournal(Long id);
}
