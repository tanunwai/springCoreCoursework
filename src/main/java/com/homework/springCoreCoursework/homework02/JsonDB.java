package com.homework.springCoreCoursework.homework02;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB {
	
	@Autowired
	private Gson gson;
	
	private static final Path PATHS=Paths.get("src\\main\\java\\com\\homework\\springCoreCoursework\\homework02\\person.json");
	
	public List<Person> queryAll() throws Exception{
		String jsonStr=Files.readAllLines(PATHS).stream().collect(Collectors.joining());
		Type type=new TypeToken<ArrayList<Person>>() {}.getType();
		List<Person> person=gson.fromJson(jsonStr, type);
		
		/*
		Date today = new Date();
		LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (Person person : people) {
			LocalDate birthLocalDate = person.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int age = todayLocalDate.getYear() - birthLocalDate.getYear();
			person.setAge(age);
		}*/
		Date today1=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(today1);
		int todayYear=calendar.get(Calendar.YEAR);
		for(Person people : person) {
			calendar.setTime(people.getBirth());
			int bithYear=calendar.get(Calendar.YEAR);
			int age=todayYear-bithYear;
			people.setAge(age);
		}
		return person;
	}
	
	public boolean addPerson(Person person) throws Exception{
		List<Person> people=queryAll();
		if(people.stream().anyMatch(p->p.equals(person))) {
			return false;
		}
		people.add(person);
		String peopleStr=gson.toJson(people);
		Files.write(PATHS, peopleStr.getBytes("UTF-8"));
		return true;
		
		//method 2
		/*
		Optional<Person> optPerson=queryAll().stream()
				 .filter(peoples->peoples.getName().equals(person.getName()))
				 //.filter(peoples->peoples.getBirth().equals(person.getBirth()))
				 //.filter(peoples->peoples.getAge().equals(person.getAge()))
				 .findFirst();
			if(optPerson.isPresent()) {
			return false;
			}else {
				people.add(person);
				String newJsonString=gson.toJson(people);
				Files.write(PATHS, newJsonString.getBytes("UTF-8"));
		return true;
		*/
	}
	
	public boolean removePerson(Person person) throws Exception{
		List<Person> people=queryAll();
		if(people.stream().anyMatch(p->p.equals(person))) {
			people.remove(person);
			String removePerson=gson.toJson(people);
			Files.write(PATHS, removePerson.getBytes("UTF-8"));
			return true;
		}else {
			return false;
		}
	}
}