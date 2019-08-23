package de.coolsafe.api.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CustomerExcerpt.class)
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
}
