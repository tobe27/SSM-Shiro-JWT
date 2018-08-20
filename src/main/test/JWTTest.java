import com.alibaba.fastjson.JSON;
import model.ResponseData;
import model.User;
import org.junit.Test;
import util.JwtUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTTest {
    @Test
    public void jwt(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "name");
        String token = JwtUtil.createToken(map,1000);
        System.out.println(token);
        System.out.println("************************");
        System.out.println("************************");
        System.out.println(JSON.toJSONString(JwtUtil.parseToken(token)));
    }

    @Test
    public void jwtTest(){
        String token = "";
        System.out.println(JSON.toJSONString(JwtUtil.parseToken(token)));
        System.out.println(JwtUtil.verifyToken(token));
    }
    @Test
    public void match(){
        String url = "/user";
        String role = "userx";
        System.out.println(url);
        System.out.println(url.indexOf(role));
    }

    @Test
    public void resultmMapTest() {
        User user = new User();
        user.setName("www");
        user.setPassword("yzs");
        user.setRole("com");
        User user1 = new User();
        user1.setName("www");
        user1.setPassword("yzs");
        user1.setRole("com");
        List list = new ArrayList();
        list.add(user);
        list.add(user1);
        ResponseData responseData = new ResponseData();
        System.out.println(JSON.toJSONString(responseData.code(200).message("success").data(list),true));
    }

}
