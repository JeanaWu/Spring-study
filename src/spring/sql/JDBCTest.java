package spring.sql;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    private ApplicationContext ctx=null;
    private JdbcTemplate jdbcTemplate;
    private EmployeeDao employeeDao;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx=new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");
        jdbcTemplate=(JdbcTemplate)ctx.getBean("jdbcTemplate");
        employeeDao=ctx.getBean(EmployeeDao.class);
        namedParameterJdbcTemplate=ctx.getBean(NamedParameterJdbcTemplate.class);
    }

    /**
     * 可以给参数取名字，维护性更高，但是较为麻烦
     */
    @Test
    public void testNamedParameterJdbcTemplate(){
        String sql ="INSERT INTO EMPLOYEES(last_name,email, dept_id) VALUES(:ln,:emai,:deptid)";

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("ln","FF");
        paramMap.put("emai","ff@163.com");
        paramMap.put("deptid",2);

        namedParameterJdbcTemplate.update(sql,paramMap);
    }

    /**
     * s使用具名参数时，可以使用如下方法namedParameterJdbcTemplate.update(String sql,SqlParameterSource paranameSource)进行更新操作
     * SQL语句中参数名和类的属性一致
     */
    @Test
    public void testNamedParameterJdbcTemplate2(){
        String sql ="INSERT INTO EMPLOYEES(last_name,email, dept_id) VALUES(:lastName,:email,:deptId)";

        Employee employee=new Employee();
        employee.setLastName("xyz");
        employee.setEmail("xyz@sina.com");
        employee.setDeptId(3);
        SqlParameterSource paranameSource=new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(sql,paranameSource);
    }
    /**
     * 测试JDBCdao的使用
     */
    @Test
    public void testEmployeeDao(){
        System.out.println(employeeDao.get(7));

    }

    /**
     * 获取单个列的值，或做统计查询
     * 使用queryForObject(String sql, Class<Long> requiredType);
     */
    @Test
    public void testQueryForObject2(){
        String sql="SELECT COUNT(ID) FROM employees";
        long count=jdbcTemplate.queryForObject(sql, Long.class);

        System.out.println(count);
    }
    /**
     * 查到实体类的集合
     * 注意调用的不是queryForObj方法
     */
    @Test
    public void testQueryForList(){
        String sql ="SELECT ID, LAST_NAME lastName, DEPT_ID,  EMAIL FROM EMPLOYEES  WHERE ID>?";
        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employees=jdbcTemplate.query(sql,rowMapper,5);
        System.out.println(employees);
    }

    /**
     * 从数据库中获取一条记录，实际得到对应的一个对象
     * 注意不是调用queryForObject(String sql, Class<Employee> requiredType, Object...args)这个方法
     * 而需要调用queryForObject(String sql,RowMapper<Employee> rowMapper,Object...args)方法
     * 1.其中的RowMapper应指定如何去映射结果集的行，乘用的实现类为BeanPropertyRowMapper
     * 2.使用SQL中列的别名完成类名和类的属性名的映射，例如last_name lastName
     * 3.不支持级联属性，jdbcTemplate到底是一个JDBC小工具而不是框架
     */
    @Test
    public void testQueryForObjec(){
        String sql ="SELECT ID, LAST_NAME lastName, DEPT_ID AS \"department.id\",  EMAIL FROM EMPLOYEES  WHERE ID=?";

        RowMapper<Employee> rowMapper=new BeanPropertyRowMapper<>(Employee.class);

        Employee employee=jdbcTemplate.queryForObject(sql,rowMapper,1);

        System.out.println(employee);
    }
    /**
     * 执行批量更新:批量的INSERT,UPDATE,DELETE
     * 最后一个参数是Object[]的LIST类型：因为修改一条记录需要一个OBJECT数组，所以修改多个需要集合
     */
    @Test
    public void testBatchUpdate(){
        String sql ="INSERT INTO employees(last_name,emaiL,dept_id) VALUES(?,?,?)";

        List<Object[]> batchArgs=new ArrayList<>();
        batchArgs.add(new Object[]{"aa","aa@gtguigu.com",1});
        batchArgs.add(new Object[]{"bb","bb@gtguigu.com",2});
        batchArgs.add(new Object[]{"cc","cc@gtguigu.com",3});
        batchArgs.add(new Object[]{"dd","dd@gtguigu.com",4});

        jdbcTemplate.batchUpdate(sql,batchArgs);
    }
    /**
     * 执行insert,update,delete
     */
    @Test
    public void testUpdate(){
        String sql="UPDATE employees SET LAST_NAME=? WHERE ID=?";
        jdbcTemplate.update(sql,"jake",5);
    }
    @Test
    public void test() throws SQLException {
        DataSource dataSource=ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}