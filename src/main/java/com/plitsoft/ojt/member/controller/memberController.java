package com.plitsoft.ojt.member.controller;


import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.req.GetMembersQueryKey;
import com.plitsoft.ojt.member.dto.res.MemberResDTO;
import com.plitsoft.ojt.member.dto.res.MemberSpecResDTO;
import com.plitsoft.ojt.member.service.memberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/member" )
public class memberController {

    @Autowired
    private memberService service;

    @GetMapping( "/{id}" )
    public MemberResDTO getMember(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find( memberId );
        return new MemberResDTO( member );
    }

    @GetMapping( "/{id}/spec" )
    public MemberSpecResDTO getMemberSpec(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find( memberId );
        return new MemberSpecResDTO( member );
    }
}
