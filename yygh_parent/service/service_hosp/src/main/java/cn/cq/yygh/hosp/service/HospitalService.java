package cn.cq.yygh.hosp.service;

import cn.cq.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * HospitalService <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 10:52 <br>
 */
public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //获取签名
    String getSignKey(String hoscode);

    //根据医院编号查询
    Hospital getByHoscode(String hoscode);
}
