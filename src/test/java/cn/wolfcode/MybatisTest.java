package cn.wolfcode;

import cn.wolfcode.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.PanelUI;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    public void selectTest(){
        //查询全部
        //List<Employee> employees = employeeMapper.selectList(null);
       // employees.forEach(System.out::println);
        Employee employee = employeeMapper.selectById(4l);
        System.out.println(employee);
        //select多个指定id
        employeeMapper.selectBatchIds(Arrays.asList(1l, 2l, 3l)).forEach(System.out::println);
        //需求： 查询name=dafei， age=18的员工信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","dafei");
        map.put("age",18);
        employeeMapper.selectByMap(map).forEach(System.out::println);
        //需求： 查询满足条件的所有的员工个数
        map.clear();
        map.put("age",25);
        System.out.println(employeeMapper.selectCount(Wrappers.<Employee>query().eq("age", 25)));
        //需求： 查询满足条件的所有的员工信息，
        employeeMapper.selectList(Wrappers.<Employee>query().eq("age", 25))
                .forEach(System.out::println);
        //需求： 查询满足条件的所有的员工信息， 返回List<Map<String, Object>>  底层将每条数据封装成HashMap
        List<Map<String, Object>> dept_id = employeeMapper.
                selectMaps(Wrappers.<Employee>query().
                        select("dept_id,count(id) count").
                                groupBy("dept_id"));
        System.out.println(dept_id);
        //需求：查询第二页员工数据， 每页显示3条， （分页返回的数据是实体对象）

        Page<Employee> page1 = new Page<>(2, 3);

                employeeMapper.selectPage(
                        page1, Wrappers.emptyWrapper());
        System.out.println(page1.getTotal());
        page1.getRecords().forEach(System.out::println);

    }
    @Test
    public void pageTest(){
        Page<Employee> page1 = new Page<>(2, 3);

        employeeMapper.selectPage(
                page1, Wrappers.emptyWrapper());
        System.out.println(page1.getTotal());
        page1.getRecords().forEach(System.out::println);
    }

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
    public void delete(){

        //删除一个
         employeeMapper.deleteById(4l);
        //多个删除
        employeeMapper.deleteBatchIds(Arrays.asList(1l,2l,3l));
        //传入map条件删除
      HashMap<String, Object> map = new HashMap<>();
      map.put("name","dafei");
      map.put("age",18);
        employeeMapper.deleteByMap(map);
        //传入wrapper条件构造器删除
        employeeMapper.delete(new QueryWrapper<Employee>()
                .eq("age",18).eq("name","dafei"));

    }
    @Test
    public void  updateTest(){
        //需求：更新name=dafei员工年龄为18岁
        //方法一 查出来后替换
//        Employee employee = employeeMapper.selectById(2l);
//        employee.setName("dafei");
//        int delete = employeeMapper.updateById(employee);
//        System.out.println(delete);
        //UPDATE employee SET age=? WHERE delete_statue=0 AND (name = ?)
//        employeeMapper.update(null,
//                new UpdateWrapper<Employee>().
//                        set("age",18).
//                        eq("name","dafei"));
       // UPDATE employee SET age='19' WHERE delete_statue=0 AND (name = ?)
        employeeMapper.update(null,
                new UpdateWrapper<Employee>().
                        setSql("age='19'").eq("name","dafei"));

    }
}
