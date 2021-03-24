package cn.cq.yygh.hosp.repository;

import cn.cq.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * HospitalRepository <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 10:50 <br>
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);
}
