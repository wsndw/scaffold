package cn.cq.yygh.hosp.repository;

import cn.cq.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * DepartmentRepository <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 16:06 <br>
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
