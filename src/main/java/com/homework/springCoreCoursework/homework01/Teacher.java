package com.homework.springCoreCoursework.homework01;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher {
	private Integer id;
	private String name;
	private Set<Clazz> clazzs;
	private List<String> expertise;
	private Map<String, Integer> salary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<String> getExpertise() {
		return expertise;
	}
	public void setExpertise(List<String> expertise) {
		this.expertise = expertise;
	}
	public Map<String, Integer> getSalary() {
		return salary;
	}
	public void setSalary(Map<String, Integer> salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", clazzs=" + clazzs + ", expertise=" + expertise + ", salary="
				+ salary + "]";
	}	
}