package com.homework.springCoreCoursework.homework01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext_1.xml");
		Student john = ctx.getBean("s1", Student.class);
		Student mary = ctx.getBean("s2", Student.class);

		System.out.println(john.toString());
		System.out.println(mary.toString());

		Teacher[] teachers = { ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class) };
		Set<Teacher> teacherSet = new LinkedHashSet<>();

		// getMary'sTeacherMethod1
		for (Teacher teacher : teachers) {
			clazz_loop: for (Clazz cla1 : teacher.getClazzs()) {
				for (Clazz cla2 : mary.getClazzs()) {
					if (cla1.getId() == cla2.getId()) {
						teacherSet.add(teacher);
						break clazz_loop;
					}
				}
			}
		}

		System.out.println(mary.getName() + "的老師(物件)" + teacherSet);
		System.out.println(
				mary.getName() + "的老師" + teacherSet.stream().map(Teacher::getName).collect(Collectors.toSet()));

		// getMary'sTeacherMethod2
		List<String> marrysTeachers = new ArrayList<>();
		mary.getClazzs().forEach(maryClazz -> {
			Arrays.asList(teachers).stream().forEach(teacher -> {
				teacher.getClazzs().forEach(teacherClazz -> {
					if (maryClazz.getId() == teacherClazz.getId()) {
						marrysTeachers.add(teacher.getName());
					}
				});
			});
		});

		System.out.println(mary.getName() + "的老師" + marrysTeachers);

		// getMary'sTeacherMethod3
		List<Object> marrysTeachers2 = new ArrayList<>();
		mary.getClazzs().forEach(maryClazz -> {
			Arrays.asList(teachers).stream().forEach(teacher -> {
				marrysTeachers2.add(
						teacher.getClazzs().stream().filter(teacherClazz -> teacherClazz.getId() == maryClazz.getId())
								.map(teacherClazz -> teacher.getName()).collect(Collectors.toList()));
			});
		});

		System.out.println(marrysTeachers2);

		// getMary'sTeacherMethod4.
		System.out.println(mary.getName()+"的老師");
		Arrays.asList(teachers).stream().filter(maryClazz -> {
			for (Clazz c : mary.getClazzs()) {
				if (maryClazz.getClazzs().contains(c)) {
					return true;
				}				
			}
			return false;
		}).forEach(teacher -> System.out.print(teacher.getName() + ","));

		((ClassPathXmlApplicationContext) ctx).close();
	}
}
