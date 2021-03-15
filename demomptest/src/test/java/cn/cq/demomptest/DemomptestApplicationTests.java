package cn.cq.demomptest;

import cn.cq.demomptest.entity.User;
import cn.cq.demomptest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemomptestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //mp复杂查询操作
    @Test
    public void testSelect(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ge、gt、le、lt
        //queryWrapper.ge("age",21);

        //eq、ne
        //queryWrapper.eq("name","Tom");

        //between、notBetween
        //queryWrapper.between("age",20,25);

        //like、notLike、likeLeft、likeRight
        //queryWrapper.like("name","张");

        //orderBy、orderByDesc、orderByAsc
        queryWrapper.orderByDesc("age", "id");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);



    }

    //根据id删除
    @Test
    public void testDeleteId(){
        int rows = userMapper.deleteById(1371359094111825921L);
        System.out.println(rows);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    //简单条件删除
    @Test
    public void testDeleteByMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","111");
        columnMap.put("age",23);
        int result = userMapper.deleteByMap(columnMap);
        System.out.println(result);
    }

    //分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1,3);
        //返回对象得到分页所有数据
        Page<User> userPage = userMapper.selectPage(page, null);
        //总页数
        long pages = userPage.getPages();
        //当前页
        long current = userPage.getCurrent();
        //查询数据集合
        List<User> records = userPage.getRecords();
        //总记录数
        long total = userPage.getTotal();
        //是否存在上下一页
        boolean hasNext = userPage.hasNext();
        boolean hasPrevious = userPage.hasPrevious();
        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }

    //简单的条件查询
    @Test
    public void testSelect2(){
        Map<String, Object> columnMap =new HashMap<>();
        columnMap.put("name","Jack");
        columnMap.put("age",20);
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    //多个id批量查询
    @Test
    public void testSelect1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //根据id查询
        User user = userMapper.selectById(1371349966622560257L);
        //修改
        user.setName("王五");
        userMapper.updateById(user);
    }

    //添加
    @Test
    public void testAdd(){
        User user = new User();
        user.setName("赵六");
        user.setAge(20);
        user.setEmail("1234@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
    //查询
    @Test
    public void findAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //修改
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1371342497712291842L);
        user.setName("lucymaryupup");
        int count = userMapper.updateById(user);
        System.out.println(count);
    }
}
