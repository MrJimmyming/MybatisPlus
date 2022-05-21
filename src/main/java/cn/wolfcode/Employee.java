package cn.wolfcode;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data


public class Employee {
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
    private int age;
    private int admin;
    private Long deptId;
    @TableLogic
    private Integer deleteStatue;
}
