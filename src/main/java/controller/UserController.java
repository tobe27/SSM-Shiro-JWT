package controller;

import dao.UserMapper;
import model.ResponseData;
import model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {
    @Resource
    UserMapper userMapper;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseData toLogin() {
        return new ResponseData().code(200).message("login page");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseData login(String name, String password, HttpServletRequest request) {
        User user = userMapper.getUserByName(name);
        if (user == null ) {
            return new ResponseData().code(400).message("user not exists");
        }
        if (!user.getPassword().equals(password)) {
           return new ResponseData().code(400).message("username or password is false");
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", user.getName());
        map.put("role",user.getRole());
        String jwt = JwtUtil.createToken(map);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
        try {
            subject.login(usernamePasswordToken);
            logger.info("登录成功");
            return new ResponseData().success().data(jwt);
        }catch (Exception e) {
            return new ResponseData().fail();
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseData toUser(HttpServletRequest request){
        return new ResponseData().code(200).message("welcome to user");
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseData tUser(HttpServletRequest request){
        return new ResponseData().code(200).message(request.getHeader("token"));
    }

    @RequestMapping(value = "/pre", method = RequestMethod.GET)
    @RequiresRoles("admin")
    public ResponseData toP(){
        return new ResponseData().code(200).message("welcome to president");
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ResponseData unAu(){
        return new ResponseData().unauthorized();
    }

}
