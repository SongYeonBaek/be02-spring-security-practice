package com.example.demo.member.controller;

import com.example.demo.member.model.Member;
import com.example.demo.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class Membercontroller {
    private MemberService memberService;

    private AuthenticationManager authenticationManager;
    //등록해둔 AuthenticationManager Bean 의존성 주입


    public Membercontroller(MemberService memberService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
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

    //formLogin을 사용하지 않기로 했으니까 Login Controller 만들어주기
    //AuthenticationManager 인터페이스를 가져와서 AuthenticationPRovider을 구현해주면 됨
    //authenticate : Manager -> Provider에게 매개변수로 사용자에게 전달받았던 id와 pw을 가지고 Token 객체를 생성해서 전달하는 것
    // (원래는 AuthenticationFilter에서 Token 생성했어야했는데 filter 안쓰기로 하고 바로 Manager로 요청을 받으니까
    // SecurityContextHolder도 Filter 대신해서 Manager가 해야함
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login (String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok().body(((Member)authentication.getPrincipal()).getId() );
    }


}
