package com.homework.springCoreCoursework.homework02;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
	private JsonDB jsonDB;

	@Override
	public boolean create(Person person) {
		boolean check = false;
		try {
			check = jsonDB.addPerson(person);
		} catch (Exception e) {
			e.getStackTrace();
			check = false;
		}
		return check;
	}

	@Override
	public boolean delete(Person person) {
		boolean check=false;
		try {
			jsonDB.removePerson(person);
			check=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public List<Person> readAll() {
		List<Person> people = null;
		try {
			people = jsonDB.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public Person findByName(String name) {
		Optional<Person> optPeople = readAll().stream().filter(psersonals -> psersonals.getName().equals(name))
				.findFirst();
		Person findPerson = optPeople.isPresent() ? optPeople.get() : null;
		return findPerson;
	}
}