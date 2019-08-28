package de.coolsafe.api.file;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource
@Repository
public interface FileRepository extends CrudRepository<File, String> {
	
	List<FileInfo> findAllProjectedBy();
}