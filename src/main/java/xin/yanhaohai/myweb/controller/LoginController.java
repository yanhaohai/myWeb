package xin.yanhaohai.myweb.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.yanhaohai.myweb.domain.User;
import xin.yanhaohai.myweb.service.UserService;
import xin.yanhaohai.myweb.utils.CodeUtils;
import xin.yanhaohai.myweb.utils.JWTResult;
import xin.yanhaohai.myweb.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate template;

    @ApiOperation("用户登录验证")
    @ApiImplicitParam(name = "user",value = "用户信息")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(User user, HttpServletResponse response, HttpServletRequest request) {
        //登录验证
        if (user.getPhone()!=null){
            //验证码登录
            String code = template.opsForValue().get(user.getPhone());
            if (user.getCode().equals(code)){
                //验证通过
                return "main";
            }
        }
        return null;
    }
    /**
     * 发送验证码
     * */
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    public String getCode(@RequestParam String phone){

        return CodeUtils.getCode(phone);

    }
    @RequestMapping(value = "/testToken",method = RequestMethod.GET)
    public Object testToken(HttpServletRequest request){
        //从请求头中获取token
        String token = request.getHeader("tokenID");

        if (token != null){
            //如果携带token
            //验证token的有效性
            JWTResult jwtResult = TokenUtils.validateJWT(token);
            if (jwtResult.isSuccess()){
                //token验证通过,未超时
                return jwtResult.getClaims().getSubject();
            }

        }
        return "false";
    }
}
