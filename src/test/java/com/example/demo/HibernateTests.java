package com.example.demo;

import com.example.demo.entities.Address;
import com.example.demo.entities.AddressType;
import com.example.demo.entities.BaseEntity;
import com.example.demo.entities.Currency;
import com.example.demo.entities.Job;
import com.example.demo.entities.Person;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@SpringBootTest
class HibernateTests {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PersonRepository personRepository;


	@Test
	void address_job_and_person_created_and_saved() {
		Address address = new Address();
		address.setStreet("Generala Shapovala");
		address.setBuildingNum("20/1");
		address.setAddressType(AddressType.HOUSE);

		//Act
		Address savedAddress = addressRepository.save(address);

		//Check
		Optional<Address> addressFromDB = addressRepository.findById(savedAddress.getId());
		AssertionErrors.assertTrue("Address saved failure", addressFromDB.isPresent());

		Job job = new Job();
		job.setPosition("Full Stack developer");
		job.setAddress(savedAddress);
		job.setStrategy("Forward business for customer's requirements");

		//Act
		Job savedJob = jobRepository.save(job);
		Optional<Job> jobFromDB = jobRepository.findById(savedJob.getId());

		//Check
		AssertionErrors.assertTrue("Job Saved failure", jobFromDB.isPresent());

		AssertionErrors.assertTrue("Address doesn't attached to Job",
				jobFromDB.
						map(Job::getAddress).
						map(BaseEntity::getId).equals(
						addressFromDB.map(BaseEntity::getId)));

		Person person = new Person();
		person.setFirstName("Vladyslav");
		person.setLastName("Zakharzhevskyi");
		person.setAge(28);
		person.addJob(job);
		person.setSalary(2800.00);

		//Act
		Person savedPerson = personRepository.save(person);

		savedAddress.setPerson(savedPerson);
		Address mergedAddress = addressRepository.save(savedAddress);

		//Check
		Optional<Person> personFromDB = personRepository.findById(savedPerson.getId());
		AssertionErrors.assertTrue("Person Saved failure", personFromDB.isPresent());

		AssertionErrors.assertTrue("Address attached to person failure!",
				personFromDB.
						map(Person::getAddresses).
						filter(addresses -> addresses.size() == 1).
						map(addresses -> addresses.get(0).getId()).equals(
							addressFromDB.
									map(BaseEntity::getId)));


		AssertionErrors.assertTrue("Job attached to person failure!",
				personFromDB.
						map(Person::getJobs).
						filter(jobs -> jobs.size() == 1 && jobs.get(0).getId().equals(savedJob.getId())).
						isPresent());
	}

	@Test
	public void hibernate_tests_parent_and_child_test_cases_saved_succesfully_all_cases() {
		Address address = new Address();
		address.setStreet("NEW_ADDRESS");
		address.setAddressType(AddressType.GOVERNMENT);
		address.setBuildingNum("25/10");

		Person person = new Person();
		person.setFirstName("Andrew");
		person.setLastName("Bertinskiy");
		person.setAge(27);
		person.setSalary(2000.00);
		person.addAddress(address);

		//1. New child object created added to new object parent -> save parent.
		//Act
		Person savedPerson = personRepository.save(person);

		//Check
		AssertionErrors.assertTrue("Person doesn't saved failure!",
				personRepository.findById(savedPerson.getId()).isPresent());

		AssertionErrors.assertTrue("Address doesn't attached to Person failure!",
				personRepository.findById(savedPerson.getId()).
						map(Person::getAddresses).
						filter(addresses -> !addresses.isEmpty()).
						isPresent());


		Person person2 = new Person();
		person2.setFirstName("Anthony");
		person2.setLastName("Hopkins");
		person2.setAge(62);
		person2.setSalary(35000.00);
		person2.setCurrency(Currency.US);

		Address address2 = new Address();
		address2.setStreet("Abrigale str.");
		address2.setBuildingNum("15/77");
		address2.setAddressType(AddressType.PRIVATE_HOUSE);
		address2.setPerson(person2);

		//1.1 New parent object created added to new object child -> save child.
		//Act
		Address savedAddress2 = addressRepository.save(address2);

		//Check
		AssertionErrors.assertTrue("Address doesn't saved failure!",
				addressRepository.findById(savedAddress2.getId()).isPresent()
		);

		AssertionErrors.assertTrue("Person doesn't attached to address failure!",
				addressRepository.findById(savedAddress2.getId()).
						map(Address::getPerson).
						filter(containsAddress(savedAddress2.getId())).
						isPresent()
		);

		//2 Parent exist, create new child object add to parent and save parent
		Address address3 = new Address();
		address3.setCity("Kyiv");
		address3.setStreet("Belorysskaya");
		address3.setBuildingNum("30A");
		address3.setAddressType(AddressType.HOUSE);

		int countBefore = personRepository.findById(person.getId()).
				map(Person::getAddresses).get().size();

		//Act
		person.addAddress(address3);
		Person savedPerson2 = personRepository.save(person);

		//Check
		AssertionErrors.assertEquals("Case 2: Address doesn't attached to person failure!",
				countBefore,
				personRepository.findById(savedPerson2.getId()).
						map(Person::getAddresses).get().size() - 1
		);


		//3 Child exist, create new parent object add child to parent and save child
		Person person3 = new Person();
		person3.setFirstName("Andrea");
		person3.setLastName("Bertolucci");
		person3.setAge(62);
		person3.setSalary(35000.00);
		person3.setCurrency(Currency.US);
		person3.addAddress(address3);

		//Act
		personRepository.save(person3);

		//Check
		AssertionErrors.assertTrue("Case 3: Person doesn't attached to Address failure!",
				addressRepository.findById(address3.getId()).
						map(Address::getPerson).
						filter(person1 -> person1.getId().equals(person3.getId())).
						isPresent()
		);

	}

	private Predicate<Person> containsAddress(UUID addressId) {
		return person -> person.getAddresses().stream()
				.map(BaseEntity::getId)
				.anyMatch(uuid -> uuid.equals(addressId));
	}

}
