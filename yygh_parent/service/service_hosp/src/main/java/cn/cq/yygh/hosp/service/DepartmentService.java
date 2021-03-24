package cn.cq.yygh.hosp.service;

import cn.cq.yygh.model.hosp.Department;
import cn.cq.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * DepartmentService <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 16:08 <br>
 */
public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    //查询科室接口
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    //删除科室接口
    void remove(String hoscode, String depcode);
}
