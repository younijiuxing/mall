package com.yq.mall.member.service;

import com.yq.common.exception.BusinessException;
import com.yq.mall.member.domain.UmsMember;
import com.yq.mall.member.request.MemberRequest;
import sun.jvm.hotspot.asm.Register;

/**
 * @author leyangjie
 * @date 2021/2/14 15:59
 * @describe
 */
public interface MemberService {
    String getOtpCode(String telphone) throws BusinessException;

    int regite(MemberRequest request) throws Exception;

    UmsMember login(String username, String password) throws BusinessException;
}
