package cn.cq.yygh.hosp.service;

import cn.cq.yygh.model.hosp.Schedule;
import cn.cq.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * ScheduleService <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-25 10:35 <br>
 */
public interface ScheduleService {

    //上传排班接口
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);
}
