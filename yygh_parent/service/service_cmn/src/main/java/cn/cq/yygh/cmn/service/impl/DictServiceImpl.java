package cn.cq.yygh.cmn.service.impl;

import cn.cq.yygh.cmn.listener.DictListener;
import cn.cq.yygh.cmn.mapper.DictMapper;
import cn.cq.yygh.cmn.service.DictService;
import cn.cq.yygh.model.cmn.Dict;
import cn.cq.yygh.vo.cmn.DictEeVo;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * DictServiceImpl <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-18 15:20 <br>
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    //导入数据字典
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper))
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导出数据字典excel
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("数据字典", "utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            List<Dict> dictList = baseMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>();
            for (Dict dict : dictList) {
                DictEeVo dictEeVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictEeVo);
                dictVoList.add(dictEeVo);
            }

            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("数据字典")
                    .doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //根据数据id查询子数据列表
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        //向list集合每个dict对象中设置hasChildren
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }


    //判断id下是否有子节点
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
