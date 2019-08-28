package de.coolsafe.api.file;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@BasePathAwareController
@Slf4j
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(method = RequestMethod.GET, value = "/files")
	public List<FileInfo> getFileIds() {
		log.info("getFileIds");
		List<FileInfo> files = fileService.getFiles();
		return files;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/files")
	public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file) {
		File dbFile = fileService.storeFile(file);
		log.info("Stored file: {}", dbFile);
		return ResponseEntity.ok().body(dbFile);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/files/{fileId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String fileId) {
		Optional<File> file = this.fileService.getFile(fileId);
		if(file.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		log.info("download file: {}", file);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get().getContentType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file.get().getData());
	}

}