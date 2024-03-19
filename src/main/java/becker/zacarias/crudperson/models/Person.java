package  becker.zacarias.crudperson.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Person {

	@Getter
	@GeneratedValue
	@Id
	private int id;

	@Getter
	@Setter
	@Column(name = "name")
	private String name;

	@Getter
	@Setter
	@Column(name = "age")
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
