package com.yq.mall.member.controller;

import com.yq.common.api.CommonResult;
import com.yq.common.exception.BusinessException;
import com.yq.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leyangjie
 * @date 2021/2/14 15:57
 * @describe
 */
@RestController
@RequestMapping("/sso")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/getOtpCode", method = RequestMethod.GET)
    public CommonResult getOtpCode(@RequestParam String telphone) throws BusinessException {
        return CommonResult.success(memberService.getOtpCode(telphone));
    }
}
