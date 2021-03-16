package cn.cq.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * HospConfig <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-16 09:58 <br>
 */
@Configuration
@MapperScan("cn.cq.yygh.hosp.mapper")
public class HospConfig {
}
