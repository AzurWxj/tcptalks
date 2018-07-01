import dao.StatusMapper;
import dao.UserService;
import model.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {
    private static final Logger logger=Logger.getLogger(UserServiceTest.class);

    @Test
    public void testRegisterUser(){
        User amy=new User("Amy","0123456789");
        int status=UserService.registerUser(amy);
        Assert.assertEquals(StatusMapper.SUCCESS,status);
    }

    @Test
    public void testValidateUser(){
        User cindy=new User("Cindy","123456");
        User amy=new User("Amy","123456");
        Assert.assertEquals(StatusMapper.SUCCESS,UserService.validateUser(cindy));
        Assert.assertEquals(StatusMapper.UNKNOWN_STATUS,UserService.validateUser(amy));
    }

    @Test
    public void testQueryUserByUsername(){
        User cindy=new User("Cindy","123456");
        logger.info("Before Query User: Cindy, the user profile is "+cindy);
        Assert.assertEquals(StatusMapper.SUCCESS,UserService.validateUser(cindy));
        logger.info("After validation, the user object contains "+cindy);
    }
}
