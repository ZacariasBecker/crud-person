package becker.zacarias.crudperson.models;

public record PersonResponseDTO(Integer id, String name, Integer age) {
	public PersonResponseDTO(Person person) {
		this(person.getId(), person.getName(), person.getAge());
	}
}
