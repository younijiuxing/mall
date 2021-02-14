package com.yq.mall.member.controller;

import com.yq.common.api.CommonResult;
import com.yq.common.exception.BusinessException;
import com.yq.mall.member.domain.UmsMember;
import com.yq.mall.member.request.MemberRequest;
import com.yq.mall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leyangjie
 * @date 2021/2/14 15:57
 * @describe
 */
@RestController
@RequestMapping("/sso")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/getOtpCode")
    public CommonResult getOtpCode(@RequestParam String telphone) throws BusinessException {
        return CommonResult.success(memberService.getOtpCode(telphone));
    }

    @PostMapping(value = "/register")
    public CommonResult register(@RequestParam String telphone) throws BusinessException {
        return CommonResult.success(memberService.getOtpCode(telphone));
    }

    @PostMapping("/registe")
    public CommonResult regite(@Valid @RequestBody MemberRequest request) throws Exception {
        int result = memberService.regite(request);
        if (result > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @PostMapping("/login")
    public CommonResult login(@RequestParam String username, @RequestParam String password) throws BusinessException {
        UmsMember member = memberService.login(username, password);
        if (member != null) {
            getHttpSession().setAttribute("member", member);
            getHttpSession().getAttribute("member");//redisSession
            return CommonResult.success(username + "登录成功!");
        }
        return CommonResult.failed();
    }
}
