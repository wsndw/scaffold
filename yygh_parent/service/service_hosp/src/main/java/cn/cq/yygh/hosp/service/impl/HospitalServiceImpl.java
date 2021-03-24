package cn.cq.yygh.hosp.service.impl;

import cn.cq.yygh.hosp.mapper.HospitalSetMapper;
import cn.cq.yygh.hosp.repository.HospitalRepository;
import cn.cq.yygh.hosp.service.HospitalService;
import cn.cq.yygh.model.hosp.Hospital;
import cn.cq.yygh.model.hosp.HospitalSet;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.QueryMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * HospitalServiceImpl <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 10:53 <br>
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalSetMapper hospitalSetMapper;

    //上传医院
    @Override
    public void save(Map<String, Object> paramMap) {
        //把map参数转换为对象hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);

        //判断是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);

        //如果存在，进行修改
        if (hospitalExist != null) {
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {//如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }


    }

    //获取签名
    @Override
    public String getSignKey(String hoscode) {

        HospitalSet hospitalSet = hospitalSetMapper.selectOne(new QueryWrapper<HospitalSet>().eq("hoscode", hoscode));
        return hospitalSet.getSignKey();
    }

    //根据医院编号查询
    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);

        return hospital;
    }
}
