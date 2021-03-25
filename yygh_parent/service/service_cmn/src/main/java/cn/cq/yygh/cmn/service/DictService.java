package cn.cq.yygh.cmn.service;

import cn.cq.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author CQ
 */
@Repository
public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChildData(Long id);

    //导出数据字典excel
    void exportData(HttpServletResponse response);

    //导入数据字典
    void importData(MultipartFile file);

    String getDictName(String dictcode, String value);

    List<Dict> findByDictCode(String dictCode);
}
