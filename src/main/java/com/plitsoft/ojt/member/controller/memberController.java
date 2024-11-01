package com.plitsoft.ojt.member.controller;

import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.req.GetMembersReqQueryDTO;
import com.plitsoft.ojt.member.dto.req.PatchMembersReqQueryDTO;
import com.plitsoft.ojt.member.dto.res.GetMemberResDTO;
import com.plitsoft.ojt.member.dto.res.GetMemberSpecResDTO;
import com.plitsoft.ojt.member.service.memberService;
import jakarta.validation.Valid;
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
    public GetMemberResDTO getMember(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find( memberId );
        return new GetMemberResDTO( member );
    }

    @GetMapping( "/{id}/spec" )
    public GetMemberSpecResDTO getMemberSpec(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find( memberId );
        return new GetMemberSpecResDTO( member );
    }

    @GetMapping( "/" )
    public List<GetMemberResDTO> getMembers(
            @ModelAttribute @Valid GetMembersReqQueryDTO reqQueryDTO
    ) {
        List<Member> members = service.findByFilter( reqQueryDTO );
        return members.stream()
                .map(GetMemberResDTO::new)
                .toList();
    }
}
