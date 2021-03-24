package cn.cq.yygh.hosp.controller.api;

import cn.cq.yygh.common.exception.YyghException;
import cn.cq.yygh.common.helper.HttpRequestHelper;
import cn.cq.yygh.common.result.Result;
import cn.cq.yygh.common.result.ResultCodeEnum;
import cn.cq.yygh.common.utils.MD5;
import cn.cq.yygh.hosp.service.DepartmentService;
import cn.cq.yygh.hosp.service.HospitalService;
import cn.cq.yygh.model.hosp.Department;
import cn.cq.yygh.model.hosp.Hospital;
import cn.cq.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ApiController <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-24 10:54 <br>
 */
@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;
    //删除科室接口
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号和科室编号
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");

        String signKey = MD5.encrypt(hospitalService.getSignKey(hoscode));
        if (!paramMap.get("sign").equals(signKey)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }

    //查询科室节课
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        //获取传递过来的科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //当前页
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        //limit
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String) paramMap.get("limit"));

        String signKey = MD5.encrypt(hospitalService.getSignKey(hoscode));
        if (!paramMap.get("sign").equals(signKey)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> department =departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(department);
    }

    //上传科室接口
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");

        String signKey = MD5.encrypt(hospitalService.getSignKey(hoscode));
        if (!paramMap.get("sign").equals(signKey)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法
        departmentService.save(paramMap);
        return Result.ok();

    }

    //查询医院接口
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");

        //判断是否一致
        String signKey = MD5.encrypt(hospitalService.getSignKey(hoscode));
        if (!paramMap.get("sign").equals(signKey)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }


    //上传医院接口
    @PostMapping("/saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        //获取传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        String hoscode = (String) paramMap.get("hoscode");

        String signKey = MD5.encrypt(hospitalService.getSignKey(hoscode));
        if (!paramMap.get("sign").equals(signKey)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }


        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoDataString = (String) paramMap.get("logoData");
        if (!StringUtils.isEmpty(logoDataString)) {
            String logoData = logoDataString.replaceAll(" ", "+");
            paramMap.put("logoData", logoData);
        }

        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();
    }
}
