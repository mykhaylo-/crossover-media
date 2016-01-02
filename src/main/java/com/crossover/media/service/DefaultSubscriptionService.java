package com.crossover.media.service;

import org.springframework.stereotype.Service;

import com.crossover.media.model.CurrentUser;

@Service("subscriptionService")
public class DefaultSubscriptionService implements SubscriptionService{

	@Override
	public void subscribe(Long journalId, CurrentUser user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unsubscribe(Long journalId, CurrentUser user) {
		// TODO Auto-generated method stub
	}

}
