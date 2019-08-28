package de.coolsafe.api.file;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

@Service
@Slf4j
public class FileService {
	@Autowired
	private FileRepository fileRepository;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, File> redisHashOperations;

	public List<FileInfo> getFiles() {
		return fileRepository.findAllProjectedBy();
	}

	public File storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			File dbFile = new File(fileName, file.getContentType(), file.getBytes());
			dbFile.setId(UUID.randomUUID().toString());
			this.redisHashOperations.put("files", dbFile.getId(), dbFile);
			return dbFile;
//			return fileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Optional<File> getFile(String fileId) {
		log.info("keys: {}, id: {}", this.redisHashOperations.keys("files"), fileId);
//		return fileRepository.findById(fileId)
//				.orElseThrow(() -> new RuntimeException("File not found with id " + fileId));
		return Optional.ofNullable(this.redisHashOperations.get("files", fileId));
	}

}
