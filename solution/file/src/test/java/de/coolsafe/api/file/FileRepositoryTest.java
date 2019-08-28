package de.coolsafe.api.file;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class FileRepositoryTest {

	@Autowired
	private FileRepository fileRepository;

	@Test
	public void shouldSaveFile_toRedis() {
		File file = new File("helloworld", "text", "helloworld".getBytes());
		File saved = fileRepository.save(file);
		assertNotNull(saved);
		
		File file1 = fileRepository.findById(saved.getId()).orElseThrow();
		assertNotNull(file1);
		
		assertArrayEquals("helloworld".getBytes(), file1.getData());
	}
}