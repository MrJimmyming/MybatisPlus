package cn.wolfcode.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Department {
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    private String name;
    private String sn;
    @TableLogic
    private Integer deleteStatue;
}
