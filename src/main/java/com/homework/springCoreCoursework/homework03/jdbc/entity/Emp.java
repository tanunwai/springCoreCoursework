package com.homework.springCoreCoursework.homework03.jdbc.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Emp {
	private Integer eid;
	private String ename;
	private Integer age;
	private Date createtime;
	private List<Job> job;
	
	public Emp() {}

	public Emp(Integer eid, String ename, Integer age, Date createtime, List<Job> job) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.age = age;
		this.createtime = createtime;
		this.job = job;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", age=" + age + ", createtime=" + createtime +", job="+(job==null ? "":job.stream().map(j->j.getJname()).collect(Collectors.toList()) )+ "]";
	}	
}