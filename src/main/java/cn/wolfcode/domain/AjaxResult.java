package cn.wolfcode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {
    private int code;
    private String msg;
    private Object data;
    public static AjaxResult success(){
        return new AjaxResult(200,"操作成功",null);
    }
    public static AjaxResult error(String msg){
        return new AjaxResult(400,msg,null);
    }
}
