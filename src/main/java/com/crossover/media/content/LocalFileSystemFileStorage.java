package com.crossover.media.content;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.crossover.media.exception.ResourceNotFoundException;

@Component
public class LocalFileSystemFileStorage implements FileStorage {

	@Value("${app.fileStoragePath}")
	private String storagePath;

	@Override
	public void saveContent(String fileGuid, byte[] content) {
		try {
			Path path = Paths.get(new File(buildFullFilePath(fileGuid)).toURI());
			Files.write(path, content, StandardOpenOption.CREATE_NEW);

		} catch (Exception e) {
			// TODO :
			e.printStackTrace();
		}
	}

	@Override
	public byte[] getContent(String fileGuid) {
		try {
			return Files.readAllBytes(Paths.get(new File(buildFullFilePath(fileGuid)).toURI()));
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}
	}

	private String buildFullFilePath(String fileGuid) {
		return storagePath + File.pathSeparator + fileGuid;
	}
}
