package de.coolsafe.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.coolsafe.api.customer.Address;
import de.coolsafe.api.customer.AddressRepository;
import de.coolsafe.api.customer.Customer;
import de.coolsafe.api.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ApiApplication {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class);
	}

	public @PostConstruct void init() {

		Address myAddress = addressRepository.save(new Address("MyStreet", "81549", "Muenchen", "Bayern"));
		log.info("Address created: {}", myAddress.toString());
		Customer savedCustomer = customerRepository
				.save(new Customer("Simon", "X", new Address("MyStreet", "81549", "Muenchen", "Bayern")));
		
		log.info("Customer created: {}", savedCustomer.toString());
		
	}
}