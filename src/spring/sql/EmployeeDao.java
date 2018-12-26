package spring.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee get(Integer id){
        String sql ="SELECT ID, LAST_NAME lastName, DEPT_ID ,  EMAIL FROM EMPLOYEES  WHERE ID=?";

        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);

        Employee employee=jdbcTemplate.queryForObject(sql,rowMapper,id);

        return employee;
    }
}

