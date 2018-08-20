
import dao.UserMapper;
import model.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class UserCRUDTest {
    @Autowired
    UserMapper userDao;

    private Logger log = Logger.getLogger(UserCRUDTest.class);


    @Test
    public void save(){
        User user = new User();
        user.setName("yanxi");
        user.setPassword("gonglue");
        System.out.println(userDao.saveUser(user)==1);
        System.out.println(System.currentTimeMillis());
        log.info("6666");
    }

    @Test
    public void delete(){
        System.out.println(userDao.deleteUserById(1)==1);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId(2);
        user.setName("杜甫");
        user.setPassword("123456");
        userDao.updateUser(user);
    }

    @Test
    public void get(){
        System.out.println(userDao.getUserById(1));
    }
}
