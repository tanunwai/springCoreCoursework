package com.homework.springCoreCoursework.homework03.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.homework.springCoreCoursework.homework03.jdbc.entity.Emp;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class EmpDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private ComboPooledDataSource dataSource;

	public List<Map<String, Object>> queryAll() {
		System.out.println("queryAll");
		String qSql = "select eid, ename, age, createtime from emp";
		return jdbcTemplate.queryForList(qSql);
	}

	public List<Emp> queryListEmps() {
		String sql = "select eid, ename, age, createtime from emp";
		List<Emp> emps = jdbcTemplate.query(sql, (rs, rowNum) -> {
			Emp emp = new Emp();
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setAge(rs.getInt("age"));
			emp.setCreatetime(rs.getTimestamp("createtime"));
			return emp;
		});
		return emps;
	}

	public List<Emp> queryListEmps2() {
		String sql = "select eid, ename, age, createtime from emp";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
	}

	public int addOne1(String ename, Integer age) {
		String sql = "insert into emp(ename, age) values(?,?)";
		int rowCount = jdbcTemplate.update(sql, ename, age);
		return rowCount;
	}

	public int addOne2(String ename, Integer age) {
		String sql = "insert into emp(ename, age) values(:ename, :age)";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("ename", ename).addValue("age", age);
		int rowCount = namedParameterJdbcTemplate.update(sql, params);
		return rowCount;
	}

	public int[] multiAdd1(List<Object[]> rows) {
		String sql = "insert into emp(ename, age) values(?,?)";
		return jdbcTemplate.batchUpdate(sql, rows);
	}

	public int[] multiAdd2(List<Emp> emps) {
		String sql = "insert into emp(ename, age) values(?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, emps.get(i).getEname());
				ps.setInt(2, emps.get(i).getAge());
			}

			@Override
			public int getBatchSize() {
				return emps.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}

	public int updateById(Integer eid, String ename, Integer age) {
		String sql = "update emp set ename=?, age=? where eid=?";
		return jdbcTemplate.update(sql, ename, age, eid);
	}

	public int deleteById(Integer eid) {
		String sql = "delete from emp where eid=?";
		return jdbcTemplate.update(sql, eid);
	}

	public int addOneTx(String ename, Integer age) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		DefaultTransactionDefinition dtf = new DefaultTransactionDefinition();
		dtf.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		int rowCount = 0;
		TransactionStatus status = transactionManager.getTransaction(dtf);

		try {
			String sql = "insert into emp(ename, age) values(?,?)";
			rowCount = jdbcTemplate.update(sql, ename, age);
			System.out.println(10 / 0);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
		return rowCount;

	}
}