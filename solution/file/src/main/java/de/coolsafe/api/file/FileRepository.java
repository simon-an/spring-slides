package de.coolsafe.api.file;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
@Repository
public interface FileRepository extends CrudRepository<File, String> {
	
	List<FileInfo> findAllProjectedBy();
}