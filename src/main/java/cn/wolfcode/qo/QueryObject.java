package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private int currentPage=1;
    private int pageSize=3;
}
