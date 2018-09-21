import com.team2.entity.User;
import com.team2.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:springmvc.xml"})
public class BaseJunit4Test {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test(){
        List<User> users=new ArrayList<>();
        User user=new User();
        user.setUserName("test");
        user.setPassword("12345");
        user.setType(0);
        users.add(user);
        userMapper.addUsers(users);
    }


}
