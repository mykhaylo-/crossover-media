package com.crossover.media.converter;

import org.springframework.stereotype.Component;

import com.crossover.media.model.Journal;
import com.crossover.media.resource.JournalResource;

@Component("journalResourceConverter")
public class JournalResourceConverter implements Converter<JournalResource, Journal>{

	@Override
	public Journal convert(JournalResource source) {
		Journal journal = new Journal();
		journal.setName(source.getName());
		journal.setId(source.getId());
		journal.setDescription(source.getDescription());
		return journal;
	}
}
