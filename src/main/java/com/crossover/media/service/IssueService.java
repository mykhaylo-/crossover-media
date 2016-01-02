package com.crossover.media.service;

import java.util.stream.Stream;

import com.crossover.media.model.Issue;
import com.crossover.media.model.Journal;

public interface IssueService {

	Stream<Issue> getIssuesForJournal(Journal journal);
	
	Issue getIssue(Long journalId, Long issueId);

	void publishNewIssue(Issue issue, byte[] content);
	
	void deleteIssue(Issue issue);
}
