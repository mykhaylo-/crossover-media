package com.crossover.media.service;

import java.util.UUID;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crossover.media.content.FileStorage;
import com.crossover.media.model.Issue;
import com.crossover.media.model.Journal;
import com.crossover.media.repository.IssueRepository;
import com.crossover.media.repository.JournalRepository;

@Service("issueService")
public class DefaultIssueService implements IssueService {

	@Resource
	private FileStorage fileStorage;

	@Resource(name = "issueRepository")
	private IssueRepository issueRepository;

	@Resource(name = "journalRepository")
	private JournalRepository journalRepository;

	@Override
	public Stream<Issue> getIssuesForJournal(Journal journal) {
		return issueRepository.findByJournal(journal);
	}

	@Override
	public Issue getIssue(Long journalId, Long issueId) {
		return issueRepository.findOneByJournalAndId(journalId, issueId);
	}

	@Override
	public void publishNewIssue(Issue issue, byte[] content) {
		Issue issueToSave = new Issue();
		String fileGuid = UUID.randomUUID().toString();
		issueToSave.setFileGuid(fileGuid);
		issueToSave.setName(issue.getName());
		issueToSave.setJournal(issue.getJournal());

		issueRepository.save(issue);
		fileStorage.saveContent(fileGuid, content);
	}

	@Override
	public void deleteIssue(Issue issue) {
		issueRepository.delete(issue);
	}
}
