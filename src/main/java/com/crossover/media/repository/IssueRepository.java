package com.crossover.media.repository;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import com.crossover.media.model.Issue;
import com.crossover.media.model.Journal;

public interface IssueRepository extends CrudRepository<Issue, Long> {

	Stream<Issue> findByJournal(Journal journal);

	Issue findOneByJournalAndId(Long journalId, Long id);
}
