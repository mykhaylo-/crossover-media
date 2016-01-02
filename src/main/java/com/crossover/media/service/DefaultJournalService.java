package com.crossover.media.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crossover.media.model.Journal;
import com.crossover.media.repository.JournalRepository;

@Service("journalService")
public class DefaultJournalService implements JournalService{

	@Resource
	private JournalRepository repository;
	
	@Override
	public Iterable<Journal> getAllJournals() {
		return repository.findAll();
	}

	@Override
	public Journal createNewJournal(Journal journal) {
		return repository.save(journal);
	}

	@Override
	public void deleteJournal(Long id) {
		repository.delete(id);
	}

	@Override
	public Journal getJournal(Long id) {
		return repository.findOne(id);
	}
}
