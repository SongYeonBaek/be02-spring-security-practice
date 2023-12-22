package com.example.demo.member.controller;

import com.example.demo.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class Membercontroller {
    private MemberService memberService;

    public Membercontroller(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup (String username, String password) {
        memberService.signup(username, password);
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mypage")
    public ResponseEntity mypage () {
        return ResponseEntity.ok().body("ok");
    }


}
