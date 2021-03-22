package cn.cq.yygh.cmn.listener;

import cn.cq.yygh.cmn.mapper.DictMapper;
import cn.cq.yygh.model.cmn.Dict;
import cn.cq.yygh.vo.cmn.DictEeVo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.BeanUtils;

/**
 * DictListener <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-22 14:28 <br>
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict =new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
