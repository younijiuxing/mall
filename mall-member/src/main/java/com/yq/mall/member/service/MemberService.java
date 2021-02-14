package com.yq.mall.member.service;

import com.yq.common.exception.BusinessException;

/**
 * @author leyangjie
 * @date 2021/2/14 15:59
 * @describe
 */
public interface MemberService {
    String getOtpCode(String telphone) throws BusinessException;
}
