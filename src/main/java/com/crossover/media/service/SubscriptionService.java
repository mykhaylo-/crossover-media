package com.crossover.media.service;

import com.crossover.media.model.CurrentUser;

public interface SubscriptionService {

	void subscribe(Long journalId, CurrentUser user);

	void unsubscribe(Long journalId, CurrentUser user);
}
