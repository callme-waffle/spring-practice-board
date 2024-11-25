package com.plitsoft.ojt.auth.controller;

import com.plitsoft.ojt.auth.dto.request.PostMemberJoinReqQueryDTO;
import com.plitsoft.ojt.auth.dto.response.PostMemberJoinResDTO;
import com.plitsoft.ojt.global.dto.CommonResponseDTO;
import com.plitsoft.ojt.member.domain.Part;
import com.plitsoft.ojt.part.service.partService;
import com.plitsoft.ojt.member.service.memberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class authController {

    @Autowired
    private final memberService memberService;

    @PostMapping("/join")
    public PostMemberJoinResDTO joinMember(
            @RequestAttribute("version") CommonResponseDTO.VersionResponseDTO version,
            @ModelAttribute @Valid PostMemberJoinReqQueryDTO joinQueryDTO
    ) {
        Part part = partService.findByName(joinQueryDTO.part());
        Long memberId = memberService.join(joinQueryDTO, part);
        return new PostMemberJoinResDTO(version, memberId);
    }
}
