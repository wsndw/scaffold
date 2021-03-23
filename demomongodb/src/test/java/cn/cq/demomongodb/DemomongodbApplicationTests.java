package cn.cq.demomongodb;

import cn.cq.demomongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class DemomongodbApplicationTests {

    //注入mongoTemplate
    @Autowired
    private MongoTemplate mongoTemplate;

    //添加操作
    @Test
    public void create() {
        User user = new User();
        user.setAge(20);
        user.setName("test");
        user.setEmail("123@qq.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    //查询表所有记录
    @Test
    public void findAll(){
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }

    //根据id查询
    @Test
    public void findId(){
        User byId = mongoTemplate.findById("60584ffd684e2f353eb71193", User.class);
        System.out.println(byId);
    }

    //条件查询
    @Test
    public void findUserList(){
        //name=test and age=20
        Query query =new Query(Criteria
                .where("name").is("test")
                .and("age").is(20));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    //模糊查询
    @Test
    public void findLikeUserList(){
        //name like test and age=20
        String name = "est";
        String  regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);

        Query query =new Query(Criteria
                .where("name").regex(pattern)
                .and("age").is(20));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    //分页查询
    @Test
    public void findPageUserList(){
        int pageNo=1;
        int pageSize=3;
        //条件构建
        String name = "est";
        String  regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);

        Query query =new Query(Criteria
                .where("name").regex(pattern)
                .and("age").is(20));

        //分页构建
        //查询记录数
        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);
        System.out.println(count);
        System.out.println(users);
    }

    //修改
    @Test
    public void updateUser() {
        User user = mongoTemplate.findById("60584ffd684e2f353eb71193", User.class);
        user.setName("test_1");
        user.setAge(25);
        user.setEmail("123456789@qq.com");
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        update.set("email", user.getEmail());
        UpdateResult result = mongoTemplate.upsert(query, update, User.class);
        long count = result.getModifiedCount();
        System.out.println(count);
    }

    //删除操作
    @Test
    public void delete() {
        Query query =
                new Query(Criteria.where("_id").is("60584ffd684e2f353eb71193"));
        DeleteResult result = mongoTemplate.remove(query, User.class);
        long count = result.getDeletedCount();
        System.out.println(count);
    }

}
