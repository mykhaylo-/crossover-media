package com.crossover.media.facade;

import java.util.List;

import com.crossover.media.resource.IssueResource;

public interface IssueFacade {

	List<IssueResource> getIssuesForJournal(Long journalId);

	IssueResource getIssue(Long journalId, Long issueId);

	void createNewIssue(Long journalId, String name, byte[] fileContent);

	void deleteIssue(Long journalId, Long issueId);
}

