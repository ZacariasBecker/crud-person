package becker.zacarias.crudperson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import becker.zacarias.crudperson.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
