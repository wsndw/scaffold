package cn.cq.yygh.hosp.repository;

import cn.cq.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ScheduleRepository <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-25 10:34 <br>
 */
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
