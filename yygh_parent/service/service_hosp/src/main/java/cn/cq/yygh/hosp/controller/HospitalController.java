package cn.cq.yygh.hosp.controller;

import cn.cq.yygh.common.result.Result;
import cn.cq.yygh.hosp.service.HospitalService;
import cn.cq.yygh.model.hosp.Hospital;
import cn.cq.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * HospitalController <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-25 14:17 <br>
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    //医院列表(条件查询分页)
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);

        return Result.ok(pageModel);
    }
}
