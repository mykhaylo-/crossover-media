package com.crossover.media.converter;

import org.springframework.stereotype.Component;

import com.crossover.media.model.Journal;
import com.crossover.media.resource.JournalResource;

@Component("journalConverter")
public class JournalConverter implements Converter<Journal, JournalResource> {

	@Override
	public JournalResource convert(Journal source) {
		JournalResource journal = new JournalResource();
		journal.setName(source.getName());
		journal.setId(source.getId());
		journal.setDescription(source.getDescription());
		return journal;
	}
}
