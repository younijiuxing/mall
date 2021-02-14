package com.yq.mall.member.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.yq.common.exception.BusinessException;
import com.yq.mall.member.config.properties.RedisKeyPrexConfigure;
import com.yq.mall.member.domain.UmsMember;
import com.yq.mall.member.domain.UmsMemberExample;
import com.yq.mall.member.mapper.UmsMemberMapper;
import com.yq.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author leyangjie
 * @date 2021/2/14 15:59
 * @describe 会员service
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisKeyPrexConfigure redisKeyPrexConfigure;

    @Override
    public String getOtpCode(String telphone) throws BusinessException {
        // 1.校验手机号在数据库是否存在
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria().andPhoneEqualTo(telphone);
        List<UmsMember> umsMemberList = umsMemberMapper.selectByExample(umsMemberExample);
        if (CollectionUtil.isNotEmpty(umsMemberList)) {
            throw new BusinessException("手机号已注册");
        }

        // 2.校验60s范围内是否已经生成过动态校验码
        String telPhoneRedisKey = redisKeyPrexConfigure.getPrefix().getOtpCode() + telphone;
        if (redisTemplate.hasKey(telPhoneRedisKey)) {
            throw new BusinessException("已发送验证码,请不要重复注册");
        }
        // 3 生成6位的验证码，并存放到redis中
        String otpCode = String.valueOf(RandomUtil.randomInt(100000, 999999));
        redisTemplate.opsForValue().set(telPhoneRedisKey, otpCode, redisKeyPrexConfigure.getExpire().getOtpCode(), TimeUnit.SECONDS);
        return otpCode;
    }
}
