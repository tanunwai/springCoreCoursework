package com.homework.springCoreCoursework.homework03.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.homework.springCoreCoursework.homework03.jdbc.template.EmpDao;

public class TemplateTest1 {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao=ctx.getBean("empDao",EmpDao.class);
		
		System.out.println(empDao.queryAll());
		
		System.out.println("==========");
		
		//String ename=empDao.queryAll().stream().filter(e->(e.get("eid")+"").equals("2")).findFirst().get().get("ename")+"";
		//System.out.println(ename);
		
		//System.out.println(empDao.queryListEmps());
		//System.out.println(empDao.queryListEmps2());
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
