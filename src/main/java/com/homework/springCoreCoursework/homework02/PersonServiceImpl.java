package com.homework.springCoreCoursework.homework02;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private Gson gson;
	
	private static final Path PATHS=Paths.get("src\\main\\java\\com\\homework\\springCoreCoursework\\homework02\\person.json");

	@Override
	public boolean append(String name, Date birth) {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		return append(person);
	}

	@Override
	public boolean append(Person person) {
		return personDao.create(person);
	}

	@Override
	public List<Person> findAllPerson() {
		return personDao.readAll();
	}

	@Override
	public Person findByName(String name) {
		Optional<Person> optional = findAllPerson().stream().filter(p -> p.getName().equals(name)).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Person getTodayBirthPerson(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Optional<Person> dateOptional = findAllPerson().stream()
				.filter(p -> (sdf.format(p.getBirth())).contains(dateString)).findFirst();
		return dateOptional.isPresent() ? dateOptional.get() : null;
	}

	@Override
	public List<Person> getByAge(int age) {
		List<Person> ageOptional = findAllPerson().stream().filter(p -> p.getAge() > age).collect(Collectors.toList());
		return ageOptional;
	}

	@Override
	public Person updateBirth(String name, int yyyy, int mm, int dd) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			Date today = new Date();
			LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			Person getPeople = findByName(name);
			getPeople.setBirth(birth);
			LocalDate birthDate = getPeople.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int age = todayLocalDate.getYear() - birthDate.getYear();
			getPeople.setAge(age);			
			return getPeople;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletedByName(String name) {
		boolean isDeleted = false;
		Person delPerson = findByName(name);
		List<Person> personList = findAllPerson();
		if (delPerson != null) {
			personList.remove(delPerson);
			String jsonString = gson.toJson(personList);
			try {
				Files.write(PATHS, jsonString.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			isDeleted = true;
		}
		return isDeleted;	
	}
}