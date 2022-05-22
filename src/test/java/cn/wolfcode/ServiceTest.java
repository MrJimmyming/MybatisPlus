package cn.wolfcode;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.QueryEmployee;
import cn.wolfcode.service.IEmployeeService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void serviceTest(){
        //service 分页实现
        BaseMapper<Employee> employeeMapper = employeeService.getBaseMapper();
        QueryEmployee queryEmployee = new QueryEmployee();
        queryEmployee.setKeyword(null);

       Page<Employee> page =  employeeService.getPage(queryEmployee);
        System.out.println(page.getTotal());
        page.getRecords().forEach(System.out::println);
           /// 3333
        //Thread
    }
    @Test
    public void departmentTest(){
        departmentMapper.selectList(null).forEach(System.out::println);
    }



}
