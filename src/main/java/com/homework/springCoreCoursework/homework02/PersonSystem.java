package com.homework.springCoreCoursework.homework02;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_2.xml");
	private PersonController personController = ctx.getBean("personController", PersonController.class);
	private boolean stop;
	public static Scanner sc = new Scanner(System.in);

	private void menu() {
		System.out.println("=========================================");
		System.out.println("1. 建立 Person 資料");
		System.out.println("2. 取得 Person 全部資料");
		System.out.println("3. 根據姓名取得 Person");
		System.out.println("4. 取得今天生日的 Person");
		System.out.println("5. 取得某一歲數以上的 Person");
		System.out.println("6. 根據姓名來修改Person的生日");
		System.out.println("7. 根據姓名來刪除Person");
		System.out.println("0. 離開");
		System.out.println("=========================================");
		System.out.print("請選擇: ");

		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			createPerson();
			break;

		case 2:
			printPerson();
			break;

		case 3:
			getPersonByName();
			break;

		case 4:
			getPersonByTodayBirth();
			break;
			
		case 5:
			getByAge();
			break;
			
		case 6:
			updateByName();
			break;
			
		case 7:
			deleteByName();
			break;

		case 0:
			System.out.println("離開系統");
			stop = true;
			break;
		}

	}

	private void createPerson() {
		System.out.print("請輸入姓名 : ");
		String name=sc.next();
		System.out.print("請輸入 生日年:ex->1901 ");
		int yyyy=sc.nextInt();
		System.out.print("請輸入 生日 月分:ex->1~12 ");
		int mm=sc.nextInt();
		System.out.print("請輸入 生日 日:ex->1~31 ");
		int dd=sc.nextInt();
		if (null != name && name.trim().length() <= 20 && yyyy >= 1901 && mm <= 12 && dd <= 31)

		{
			personController.addPerson(name, yyyy, mm, dd);
		} else {
			System.out.println("請輸入正確的格式");
		}

	}

	private void printPerson() {
		personController.printAllPersons();
	}

	private void getPersonByName() {
		System.out.println("請輸入姓名:");
		String name = sc.next();
		if (null != name && name.trim().length() <= 20) {
			Person personal = personController.getPersonByName(name);
			generatorResult(personal);
		} else {
			System.out.println("請輸入正確的格式");
		}
	}

	private void getPersonByTodayBirth() {
		System.out.print("請輸入 生日 月 日(ex:12/06): ");
		String dateString = sc.next();
		if (null != dateString && dateString.trim().contains("/") && dateString.length() <= 5) {
			Person personal = personController.getTodayBirthPerson(dateString);
			if(null == personal) {
				throw new NullPointerException("沒有找到今天生日的人");
			}
			generatorResult(personal);
		} else {
			System.out.println("請輸入正確的格式");
		}
	}
	
	private void getByAge() {
		System.out.print("請輸入 年齡(ex:50): ");
		int age=sc.nextInt();
		if(age<100 && age>0) {
			List<Person> person=personController.getByAge(age);
			generatorResult(person);
		}else {
			System.out.println("請輸入正確的格式");
		}
	}
	
	private void updateByName() {
		System.out.print("請輸入姓名 : ");
		String name=sc.next();
		System.out.print("請輸入更新後 生日年:ex->1901 ");
		int yyyy=sc.nextInt();
		System.out.print("請輸入更新後 生日 月分:ex->1~12 ");
		int mm=sc.nextInt();
		System.out.print("請輸入更新後 生日 日:ex->1~31 ");
		int dd=sc.nextInt();
		if(null != name && name.trim().length() <= 20 
				&& yyyy>=1901 && mm<=12 && dd<=31) 
		{
			Person upPerson=personController.updateBirthByName(name, yyyy, mm, dd);
			generatorResult(upPerson);
		}else {
			System.out.println("請輸入正確的格式");
		}
	}

	private void deleteByName() {
		System.out.println("請輸入姓名:");
		String name = sc.next();
		if (null != name && name.trim().length() <= 20) {
			System.out.println("確認刪除嗎？(y/n)");
			String isChecked=sc.next();
			if(null != isChecked && isChecked.trim().equalsIgnoreCase("y")) {
				boolean isDeleted = personController.isDeleted(name);
				if (isDeleted) {
					personController.printAllPersons();
				}
			}else {
				System.out.println("資料未刪除");
			}
			
		} else {
			System.out.println("請輸入正確的格式");
		}
	}

	public static void generatorResult(Person personal) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |");
		System.out.println("+--------------+---------+--------------+");

		String birth = sdf.format(personal.getBirth());
		System.out.printf("| %-12s | %7d | %12s |\n", personal.getName(), personal.getAge(), birth);
		System.out.println("+--------------+---------+--------------+");
	}

	public static void generatorResult(List<Person> people) {
		for (Person p : people) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |");
			System.out.println("+--------------+---------+--------------+");

			String birth = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birth);
			System.out.println("+--------------+---------+--------------+");
		}
	}

	public void start() {
		while (!stop) {
			menu();
		}
	}

	public static void main(String[] args) {
		try {
			new PersonSystem().start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}