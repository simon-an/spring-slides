package de.coolsafe.api.file;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RedisHash("files")
@RequiredArgsConstructor
@ToString(exclude = { "data" })
@Data
public class File implements Serializable{
	
	private static final long serialVersionUID = -6664327637811237556L;

	@Id
	String id;

	@NonNull
	@org.springframework.lang.NonNull
	String filename;

	@NonNull
	@org.springframework.lang.NonNull
	String contentType;

	@NonNull
	@org.springframework.lang.NonNull
	byte[] data;

}
