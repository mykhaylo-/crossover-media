package com.crossover.media.content;

public interface FileStorage {

	void saveContent(String fileGuid, byte[] content);
	// TODO : use InputStream to avoid heavy memory allocation for huge files
	byte[] getContent(String fileGuid);
}
