package cn.cq.yygh.hosp.controller;

import cn.cq.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * yygh_parent
 *
 * @author : CQ
 * @date : 2021-03-15 22:54
 **/
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    /**
     * 注入service
     */
    @Autowired
    private HospitalSetService hospitalSetService;


    //1. 查询医院设置表所有信息


}
