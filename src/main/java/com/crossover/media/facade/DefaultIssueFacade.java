package com.crossover.media.facade;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crossover.media.converter.Converter;
import com.crossover.media.model.Issue;
import com.crossover.media.model.Journal;
import com.crossover.media.resource.IssueResource;
import com.crossover.media.service.IssueService;

@Component("issueFacade")
public class DefaultIssueFacade implements IssueFacade {

	@Resource(name = "issueService")
	private IssueService issueService;

	@Resource(name = "journalConverter")
	private Converter<Issue, IssueResource> issueConverter;

	@Resource(name = "journalResourceConverter")
	private Converter<IssueResource, Issue> issueResourceConverter;

	@Override
	public List<IssueResource> getIssuesForJournal(Long journalId) {
		Journal journal = new Journal();
		journal.setId(journalId);

		return issueService.getIssuesForJournal(journal).map(issue -> issueConverter.convert(issue))
				.collect(Collectors.toList());
	}

	@Override
	public IssueResource getIssue(Long journalId, Long issueId) {
		Issue issue = issueService.getIssue(journalId, issueId);
		return issueConverter.convert(issue);
	}

	@Override
	public void createNewIssue(Long journalId, String name, byte[] fileContent) {
		Issue issue = new Issue();
		issue.setName(name);
		Journal journal = new Journal();
		journal.setId(journalId);
		issue.setJournal(journal);
		issueService.publishNewIssue(issue, fileContent);
	}

	@Override
	public void deleteIssue(Long journalId, Long issueId) {
		Issue issue = new Issue();
		issue.setId(issueId);
		Journal journal = new Journal();
		journal.setId(journalId);
		issue.setJournal(journal);
		issueService.deleteIssue(issue);
	}
}
