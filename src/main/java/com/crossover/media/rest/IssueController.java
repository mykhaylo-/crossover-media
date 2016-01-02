package com.crossover.media.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crossover.media.facade.IssueFacade;
import com.crossover.media.resource.IssueResource;

@RestController
@RequestMapping("/journals/{journalId}/issues")
public class IssueController {

	@Resource(name = "issueFacade")
	private IssueFacade issueFacade;

	@RequestMapping(method = RequestMethod.GET)
	public List<IssueResource> getAllIssues(@PathVariable Long journalId) {
		return issueFacade.getIssuesForJournal(journalId);
	}

	@RequestMapping(path="/{issueId}", method = RequestMethod.GET)
	public IssueResource getIssue(@PathVariable Long journalId, @PathVariable Long issueId) {
		return issueFacade.getIssue(journalId, issueId);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void publishNewIssue(@PathVariable Long journalId, @RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws Exception {
		issueFacade.createNewIssue(journalId, name, file.getBytes());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteIssue(@PathVariable Long journalId, @PathVariable Long issueId) {
		issueFacade.deleteIssue(journalId, issueId);
	}
}
