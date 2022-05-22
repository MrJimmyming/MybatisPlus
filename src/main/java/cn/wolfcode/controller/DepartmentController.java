package cn.wolfcode.controller;

import cn.wolfcode.domain.AjaxResult;
import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryDepartment;
import cn.wolfcode.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @GetMapping
    public List<Department> list(){
        return departmentService.list();
    }
    @GetMapping("/detail")
    public Department detail(Long id){
        return departmentService.getById(id);
    }
    @GetMapping("/page")
    public Page<Department> page(QueryDepartment queryDepartment){

        return departmentService.getPage(queryDepartment);
    }

    @PostMapping
    public AjaxResult save(Department department){
        departmentService.save(department);
        return AjaxResult.success();
    }
    @PutMapping("/edit")
    public AjaxResult edit(Department department){
        departmentService.updateById(department);
        return AjaxResult.success();
    }
    @DeleteMapping("/delete")
    public AjaxResult delete(Long id){
        departmentService.removeById(id);
        return AjaxResult.success();
    }
}
