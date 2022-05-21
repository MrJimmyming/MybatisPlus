package cn.wolfcode;

import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.queryEmployee;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.impl.EmployeeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void serviceTest(){
        //service 分页实现
        BaseMapper<Employee> employeeMapper = employeeService.getBaseMapper();
        queryEmployee queryEmployee = new queryEmployee();
        queryEmployee.setKeyword(null);

       Page<Employee> page =  employeeService.getPage(queryEmployee);
        System.out.println(page.getTotal());
        page.getRecords().forEach(System.out::println);

    }


}
