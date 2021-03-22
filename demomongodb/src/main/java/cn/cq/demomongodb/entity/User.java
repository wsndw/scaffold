package cn.cq.demomongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User <br>
 *
 * @author CQ <br>
 * @version 1.0 <br>
 * @date 2021-03-22 16:01 <br>
 */
@Data
@Document("User")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}
