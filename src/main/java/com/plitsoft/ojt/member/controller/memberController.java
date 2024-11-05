package com.plitsoft.ojt.member.controller;

import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.common.UpdateServiceDTO;
import com.plitsoft.ojt.member.dto.request.GetMembersReqQueryDTO;
import com.plitsoft.ojt.member.dto.request.PatchMembersReqQueryDTO;
import com.plitsoft.ojt.member.dto.response.GetMemberResDTO;
import com.plitsoft.ojt.member.dto.response.GetMemberSpecResDTO;
import com.plitsoft.ojt.member.dto.response.PatchMemberResDTO;
import com.plitsoft.ojt.member.service.memberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class memberController {

    @Autowired
    private memberService service;

    @GetMapping("/{id}")
    public GetMemberResDTO getMember(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find(memberId);
        return new GetMemberResDTO(member);
    }

    @GetMapping("/{id}/spec")
    public GetMemberSpecResDTO getMemberSpec(
            @PathVariable("id") Long memberId
    ) {
        Member member = service.find(memberId);
        return new GetMemberSpecResDTO(member);
    }

    @GetMapping("/")
    public List<GetMemberResDTO> getMembers(
            @ModelAttribute @Valid GetMembersReqQueryDTO reqQueryDTO
    ) {
        List<Member> members = service.findByFilter(reqQueryDTO);
        return members.stream()
                .map(GetMemberResDTO::new)
                .toList();
    }

    @PatchMapping("/{id}")
    public PatchMemberResDTO patchMember(
            @PathVariable("id") Long memberId,
            @RequestParam @Valid PatchMembersReqQueryDTO requestQueryDTO
    ) {
        UpdateServiceDTO updateResult = service.update(memberId, requestQueryDTO);
        return new PatchMemberResDTO(updateResult);
    }
}
