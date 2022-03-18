package com.homework.springCoreCoursework.homework02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;

	public void addPerson(String name, int yyyy, int mm, int dd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			addPerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPerson(String name, Date birth) {
		boolean check = personService.append(name, birth);
		System.out.println("add person:" + check);
		;
	}

	public void printAllPersons() {
		// System.out.println(personService.findAllPerson());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Person> people = personService.findAllPerson();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |");
		System.out.println("+--------------+---------+--------------+");
		for (Person p : people) {
			String birth = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birth);
			System.out.println("+--------------+---------+--------------+");
		}
	}

	public Person getPersonByName(String name) {
		return personService.findByName(name);
	}

	public Person getTodayBirthPerson(String dateString) {
		return personService.getTodayBirthPerson(dateString);
	}

	public List<Person> getByAge(int age) {
		return personService.getByAge(age);
	}

	public Person updateBirthByName(String name, int yyyy, int mm, int dd) {
		return personService.updateBirth(name, yyyy, mm, dd);
	}

	public boolean isDeleted(String name) {
		return personService.deletedByName(name);
	}
}
/*
 * 功能:
 * 1. 建立 Person 資料
 * 		-> 輸入 name, birth
 * 2. 取得 Person 全部資料
 * 		-> 不用輸入參數
 * 3. 根據姓名取得 Person
 * 		-> 輸入 name
 * 4. 取得今天生日的 Person-non
 * 		-> 輸入今天日期
 * 5. 取得某一歲數以上的 Person
 * 		-> 輸入 age
 * 6. 根據姓名來修改Person的生日
 * 		-> 輸入 name, birth
 * 7. 根據姓名來刪除Person
 * 		-> 輸入 name
 * */