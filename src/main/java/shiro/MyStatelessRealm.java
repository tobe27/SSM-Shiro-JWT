package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JwtUtil;

public class MyStatelessRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyStatelessRealm.class);

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
    /**
     * Realm授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("授权开始");
        String jwt = (String) principals.getPrimaryPrincipal();
        if (JwtUtil.verifyToken(jwt)) {
            String role = (String) JwtUtil.parseToken(jwt).get("role");
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(role);
            logger.info("授权成功");
            return simpleAuthorizationInfo;
        }
        logger.info("授权失败");
        return null;
    }

    /**
     * Realm登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String jwt = (String) usernamePasswordToken.getPrincipal();
        if (JwtUtil.verifyToken(jwt)) {
            return new SimpleAuthenticationInfo(jwt, jwt ,getName());
        }
        return null;
    }
}
