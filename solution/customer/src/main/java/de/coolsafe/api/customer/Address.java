package de.coolsafe.api.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@org.springframework.data.annotation.Immutable
public class Address {

	@GeneratedValue @Id
	private Long id;
	private final String street, zipCode, city, state;

	Address() {

		this.street = null;
		this.zipCode = null;
		this.city = null;
		this.state = null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("%s, %s %s, %s", street, zipCode, city, state);
	}
}
