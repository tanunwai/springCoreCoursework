package com.homework.springCoreCoursework.homework03.jdbc.entity;

public class Job {
	private Integer jid;
	private String jname;
	private Integer eid;
	private Emp emp;
	
	public Job() {}

	public Job(Integer jid, String jname, Integer eid, Emp emp) {
		super();
		this.jid = jid;
		this.jname = jname;
		this.eid = eid;
		this.emp = emp;
	}

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
		this.jid = jid;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	
	@Override
	public String toString() {
		return "Job [jid=" + jid + ", jname=" + jname + ", eid=" + eid + ", emp=" + (emp==null?"":emp.getEname()) + "]";
	}
}