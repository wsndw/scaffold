package cn.cq.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * UserData <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-22 13:21 <br>
 */
@Data
public class UserData {
    @ExcelProperty(value = "用户编号",index = 0)
    private int uid;
    @ExcelProperty(value = "用户名称",index = 1)
    private String username;
}
