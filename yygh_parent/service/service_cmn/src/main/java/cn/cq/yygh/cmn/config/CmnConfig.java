package cn.cq.yygh.cmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * CmnConfig <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-16 09:58 <br>
 */
@Configuration
@MapperScan("cn.cq.yygh.cmn.mapper")
public class CmnConfig {
}
