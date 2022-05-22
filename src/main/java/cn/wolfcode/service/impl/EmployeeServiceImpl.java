package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.queryEmployee;
import cn.wolfcode.service.IEmployeeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public Page<Employee> getPage(queryEmployee queryEmployee) {
       return super.page(new Page<Employee>(queryEmployee.getCurrentPage(),queryEmployee.getPageSize()),
                Wrappers.<Employee>lambdaQuery().like(StringUtils.hasText(queryEmployee.getKeyword()),Employee::getName,queryEmployee.getKeyword()))
       ;
    }
}
