package cn.cq.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * TestRead <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-22 13:37 <br>
 */
public class TestRead {
    public static void main(String[] args) {
        //读取文件路径
        String fileName="E:\\excel\\01.xlsx";

        //调用方法
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
