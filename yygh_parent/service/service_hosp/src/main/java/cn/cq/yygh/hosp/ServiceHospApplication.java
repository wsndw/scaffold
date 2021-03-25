package cn.cq.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * yygh_parent
 *
 * @author : CQ
 * @date : 2021-03-15 22:06
 **/
@SpringBootApplication
@ComponentScan(basePackages = "cn.cq")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.cq")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
