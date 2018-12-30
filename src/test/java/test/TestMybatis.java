package test;

import com.alibaba.fastjson.JSON;
import com.youyicn.dao.cycle.UserMapper;
import com.youyicn.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:context/spring-mybatis.xml"})  
  
public class TestMybatis {  
	
    private static Logger logger = Logger.getLogger(TestMybatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private UserMapper userMapper;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userMapper.getbyId(1);
        System.out.println("1");
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    }

    @Test
    public void potScoreItemTest(){
        StringBuilder scoreItemBuilder = new StringBuilder();
        String[] itemNames = {"理论", "操作"};
        String[] itemScores = {"100", "100"};
        if (itemScores.length != itemNames.length) {
            System.out.println("参数个数对应不一致！");
        }
        for (int i = 0; i < itemNames.length; i++) {
            scoreItemBuilder.append(itemNames[i]).append(":").append(itemScores[i]);
            if (i < itemNames.length - 1) {
                scoreItemBuilder.append(",");
            }
        }
        System.out.println(scoreItemBuilder.toString());
    }
}  