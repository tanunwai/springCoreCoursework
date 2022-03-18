package com.homework.springCoreCoursework.homework02;

import java.util.Date;
import java.util.List;

public interface PersonService {
	boolean append(String name, Date birth);
	boolean append(Person person);
	List<Person> findAllPerson();
	Person findByName(String name);
	Person getTodayBirthPerson(String dateString);
	List<Person> getByAge(int age);
	Person updateBirth(String name, int yyyy, int mm, int dd);
	boolean deletedByName(String name);
}