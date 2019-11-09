package xin.yanhaohai.myweb.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 通过手机号发送验证码
 */
@Component
public class CodeUtils {
    @Autowired
    private static StringRedisTemplate template;

    public CodeUtils(StringRedisTemplate template) {
        this.template = template;
    }

    public static String getCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FpavCUUxABQMto3W3kZ", "QaPkcvSUCn8ZiZIjX5TceqsZQVKUI7");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "于都臻品博客网站");
        request.putQueryParameter("TemplateCode", "SMS_175240842");
        //设置验证码的值
        int code = (int)(Math.random() * 10000);
        //System.out.println(code);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            //获取本次其请求的返回状态码
//            System.out.println(response.getHttpStatus());

            if (response.getHttpStatus()==200){
                //将验证码存入redis中,设置超时时间为五分钟
                template.opsForValue().set(phone,String.valueOf(code),60*5, TimeUnit.SECONDS);
                //System.out.println("cunruo");
            }
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

}
