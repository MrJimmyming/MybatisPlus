package cn.wolfcode;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest2 {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void insert(){
        Employee employee = employeeMapper.selectById(1l);
        employee.setAge(55);
        employee.setName("jack");
        //employee.setId(null);
        int insert = employeeMapper.insert(employee);
        System.out.println(employee.getId());
        System.out.println(insert);
    }

    @Test
    public void  updateTest(){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.set("name","dafei1").eq("id","2");

        employeeMapper.update(null,wrapper);
    }
    @Test
    public void  queryLambdaTest(){
        LambdaUpdateWrapper<Employee> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Employee::getName,"dafei1").set(Employee::getAge,35).eq(Employee::getName,"dafei");
        employeeMapper.update(null,wrapper);

    }

}
