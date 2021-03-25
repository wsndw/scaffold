package cn.cq.yygh.cmn.controller;

import cn.cq.yygh.cmn.service.DictService;
import cn.cq.yygh.common.result.Result;
import cn.cq.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DictController <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-18 15:54 <br>
 */
@Api("数据字典接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    //根据dictcode查询下级节点
    @ApiOperation("根据dictcode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode){
        List<Dict> dictList=dictService.findByDictCode(dictCode);
        return Result.ok(dictList);
    }


    //导入数据字典
    @ApiOperation("导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importData(file);
        return Result.ok();
    }

    //导出数据字典excel
    @ApiOperation("导出")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    //根据数据id查询子数据列表
    @ApiOperation("根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }


    //根据dictcode和value值查询
    @GetMapping("getName/{dictcode}/{value}")
    public String getName(@PathVariable String dictcode,
                          @PathVariable String value) {
        String dictName = dictService.getDictName(dictcode, value);
        return dictName;
    }

    //根据value查询
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value) {
        String dictName = dictService.getDictName("", value);
        return dictName;
    }
}
