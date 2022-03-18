package com.homework.springCoreCoursework.homework03.jdbc.aop;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.homework.springCoreCoursework.homework03.jdbc.entity.AopMethod;

@Component
@Aspect
@Order(1)
public class LogQueryAll {
	
	@Autowired
	private Gson gson;
	
	private static final Path PATHS=Paths.get("src\\main\\java\\com\\homework\\springCoreCoursework\\homework03\\jdbc\\aop\\AopMethod.json");
	
	@Pointcut(value = "execution(* com.homework.springCoreCoursework.homework03.jdbc.template.EmpDao.queryAll(..))")
	public void pt() {}
	
	@After(value = "pt()")
	public void after() throws IOException {
		String linkStr=Files.readAllLines(PATHS).stream().collect(Collectors.joining());
		Type type=new TypeToken<ArrayList<AopMethod>>() {}.getType();
		List<AopMethod> aopMethodSave=gson.fromJson(linkStr, type);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		
		AopMethod aMethod=new AopMethod();
		aMethod.setMethodName("queryAll");
		aMethod.setLogTimestamp(sdf.format(date));
		
		aopMethodSave.add(aMethod);
		String jsonStr=gson.toJson(aopMethodSave);
		Files.write(PATHS, jsonStr.getBytes("UTF-8"));
		
		System.out.println(sdf.format(date));
	}
}
