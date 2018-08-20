package shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JwtUtil;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyStatelessShiroFilter extends AccessControlFilter {
    private Logger logger = LoggerFactory.getLogger(MyStatelessShiroFilter.class);
    /**
     *返回false
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return 返回结果是false的时候才会执行下面的onAccessDenied方法
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("is access allowed");
        return false;
    }

    /**
     * 从请求头获取token并验证，验证通过后交给realm进行登录
     * @param servletRequest
     * @param servletResponse
     * @return 返回结果为true时，表明登录认证通过，执行controller层
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("on access denied");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (JwtUtil.verifyToken(jwt)) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
            try {
                //委托realm进行登录认证
                getSubject(servletRequest, servletResponse).login(usernamePasswordToken);
                return true;
            }catch (Exception e) {
                return false;
            }
        }
        redirectToLogin(servletRequest,servletResponse);
        return false;
    }

    /**
     * 重定向到登录页
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        logger.info("redirectToLogin");
        WebUtils.issueRedirect(request, response, "/login");
    }
}
