package de.coolsafe.api.file;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("files")
public interface FileInfo {
	String getId();
	String getFilename();
	String getContentType();
}
