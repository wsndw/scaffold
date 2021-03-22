package cn.cq.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * ExcelListener <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-22 13:32 <br>
 */
public class ExcelListener extends AnalysisEventListener<UserData> {
    //一行一行读取excel内容，从第二行读取
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }

    //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
