package cn.wolfcode;

import cn.wolfcode.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void selectTest(){
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
    }
    @Test
    public void insert(){
        Employee employee = employeeMapper.selectById(1l);
        employee.setAge(55);
        employee.setName("jack");
        employee.setId(null);
        int insert = employeeMapper.insert(employee);
        System.out.println(insert);
    }
    @Test
    public void delete(){
        int delete = employeeMapper.deleteById(1527260762422206466l);
    }
    @Test
    public void  updateTest(){
        Employee employee = employeeMapper.selectById(2l);
        employee.setName("dafei");
        int delete = employeeMapper.updateById(employee);
        System.out.println(delete);
    }
}
