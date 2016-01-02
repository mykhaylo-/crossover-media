package com.crossover.media.rest;

import javax.annotation.Resource;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.media.model.CurrentUser;
import com.crossover.media.service.SubscriptionService;

@RestController
@RequestMapping(path = "/journals/{journalId}/subscribers")
public class SubscriptionController {

	@Resource(name = "subscriptionService")
	private SubscriptionService subscriptionService;

	@RequestMapping(method = RequestMethod.POST)
	public void subscribe(@PathVariable("journalId") Long journalId, @AuthenticationPrincipal CurrentUser user) {
		subscriptionService.subscribe(journalId, user);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void unsubscribe(@PathVariable("journalId") Long journalId, @AuthenticationPrincipal CurrentUser user) {
		subscriptionService.unsubscribe(journalId, user);
	}
}
