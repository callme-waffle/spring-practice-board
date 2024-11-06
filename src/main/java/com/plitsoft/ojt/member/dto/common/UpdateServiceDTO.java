package com.plitsoft.ojt.member.dto.common;

import java.util.List;

import com.plitsoft.ojt.member.domain.Member;

public record UpdateServiceDTO(
        int acknowledgedCount,
        int foundCount,
        int updatedCount,
        List<Member> member
){}