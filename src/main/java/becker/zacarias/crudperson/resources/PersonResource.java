package becker.zacarias.crudperson.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import becker.zacarias.crudperson.models.Person;
import becker.zacarias.crudperson.models.PersonRequestDTO;
import becker.zacarias.crudperson.models.PersonResponseDTO;
import becker.zacarias.crudperson.repositories.PersonRepository;

@RestController
@RequestMapping(path = "/person")
public class PersonResource {

	private PersonRepository personRepository;

	public PersonResource(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	@GetMapping
	public ResponseEntity<List<PersonResponseDTO>> getAll() {
		List<PersonResponseDTO> persons = new ArrayList<>();
		persons = personRepository.findAll().stream().map(PersonResponseDTO::new).toList();
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<PersonResponseDTO>> getById(@PathVariable Integer id) {
		Optional<PersonResponseDTO> person;
		try {
			person = personRepository.findById(id).map(PersonResponseDTO::new);
			return new ResponseEntity<Optional<PersonResponseDTO>>(person, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PersonResponseDTO>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<PersonResponseDTO> save(@RequestBody PersonRequestDTO data) {
		Person personData = new Person(data);
		personRepository.save(personData);
		return new ResponseEntity<>(new PersonResponseDTO(personData), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<PersonResponseDTO>> deleteById(@PathVariable Integer id) {
		try {
			personRepository.deleteById(id);
			return new ResponseEntity<Optional<PersonResponseDTO>>(HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PersonResponseDTO>>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody PersonRequestDTO newPerson) {
		return personRepository.findById(id).map(person -> {
			person.setName(new Person(newPerson).getName());
			person.setAge(new Person(newPerson).getAge());
			Person personUpdated = personRepository.save(person);
			return ResponseEntity.ok().body(personUpdated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
