package xin.yanhaohai.myweb.compont;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitClass implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private RedisTemplate template;
    /**
     * 项目启动时执行
     * */
    @PostConstruct
    public void setRedisS() {
//        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(stringRedisSerializer);
//
//        template.opsForHash().put("sss","onew","ssf");

    }

    /**
     * 项目启动后执行
     * */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {//保证只执行一次
            //需要执行的方法

        }
    }
}
