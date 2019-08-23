package de.coolsafe.api.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "detailed", types = Customer.class)
public interface CustomerExcerpt {

	String getFirstName();

	String getLastName();

//	@Value("#{@userService.checkAccess(target)? target.address.toString() : null}")
	@Value("#{target.address.toString()}")
	String getAddress();
}