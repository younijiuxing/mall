package com.yq.mall.member.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leyangjie
 * @date 2021/2/14 16:10
 * @describe
 */
@ConfigurationProperties(prefix = "redis.key")
@Component
@Data
public class RedisKeyPrexConfigure {
    private RedisKeyPrexConfigure.Prefix prefix;

    private RedisKeyPrexConfigure.Expire expire;

    @Data
    public static class Prefix{
        private String otpCode;

    }

    @Data
    public static class Expire{

        private Long otpCode;

    }
}
