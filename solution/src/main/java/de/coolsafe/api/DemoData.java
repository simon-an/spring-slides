package de.coolsafe.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.coolsafe.api.customer.Address;
import de.coolsafe.api.customer.AddressRepository;
import de.coolsafe.api.customer.Customer;
import de.coolsafe.api.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "coolsafe", name = "create-demo-data", havingValue = "true")
@Profile("local")
public class DemoData {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AddressRepository addressRepository;
	
	public @PostConstruct void init() {

		Address myAddress = addressRepository.save(new Address("MyStreet", "81549", "Muenchen", "Bayern"));
		log.info("Address created: {}", myAddress.toString());
		Customer savedCustomer = customerRepository
				.save(new Customer("Simon", "X", new Address("MyStreet", "81549", "Muenchen", "Bayern")));
		
		log.info("Customer created: {}", savedCustomer.toString());
		
	}
}
