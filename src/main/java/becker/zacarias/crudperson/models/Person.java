package  becker.zacarias.crudperson.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@GeneratedValue
	@Id
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person(PersonRequestDTO data) {
		this.name = data.name();
		this.age = data.age();
	}
}
