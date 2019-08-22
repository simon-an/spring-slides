package de.coolsafe.api.customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	@org.springframework.lang.NonNull
	private String firstName, lastName;
	
	@Version
	private Long version;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@NonNull
	@org.springframework.lang.NonNull
	private Address address;

	Customer() {
		this.firstName = null;
		this.lastName = null;
		this.address = null;
		this.version = null;
	}

}
