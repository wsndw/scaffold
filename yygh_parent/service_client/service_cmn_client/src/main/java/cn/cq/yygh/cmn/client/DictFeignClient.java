package cn.cq.yygh.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * DictFeignClient <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-25 14:46 <br>
 */
@FeignClient("service-cmn")
@Repository
public interface DictFeignClient {
    //根据dictcode和value值查询
    @GetMapping("/admin/cmn/dict/getName/{dictcode}/{value}")
    public String getName(@PathVariable("dictcode") String dictcode,
                          @PathVariable("value") String value);
    //根据value查询
    @GetMapping("/admin/cmn/dict/getName/{value}")
    public String getName(@PathVariable("value") String value);
}
