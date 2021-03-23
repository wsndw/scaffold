package cn.cq.demomongodb.repository;

import cn.cq.demomongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * UserRepository <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-23 11:17 <br>
 */
public interface UserRepository extends MongoRepository<User,String> {

}
