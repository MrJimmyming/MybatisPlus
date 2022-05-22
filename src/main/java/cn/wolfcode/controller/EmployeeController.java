package cn.wolfcode.controller;

import cn.wolfcode.domain.AjaxResult;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.QueryEmployee;
import cn.wolfcode.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @GetMapping
    public List<Employee> list(){
        return employeeService.list();
    }
    @GetMapping("/detail")
    public Employee detail(Long id){
        return employeeService.getById(id);
    }
    @GetMapping("/page")
    public Page<Employee> page(QueryEmployee queryEmployee){

        return employeeService.getPage(queryEmployee);
    }

    @PostMapping
    public AjaxResult save(Employee employee){
        employeeService.save(employee);
        return AjaxResult.success();
    }
    @PutMapping("/edit")
    public AjaxResult edit(Employee employee){
        employeeService.updateById(employee);
        return AjaxResult.success();
    }
    @DeleteMapping("/delete")
    public AjaxResult delete(Long id){
        employeeService.removeById(id);
        return AjaxResult.success();
    }
}
