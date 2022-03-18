package com.homework.springCoreCoursework.homework02;

import java.util.List;

public interface PersonDao {
	boolean create(Person person);
	boolean delete(Person person);
	List<Person> readAll();
	Person findByName(String name);
}
