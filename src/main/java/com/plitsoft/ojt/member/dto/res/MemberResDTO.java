package com.plitsoft.ojt.member.dto.res;

import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.domain.UserRole;
import lombok.Getter;

@Getter
public sealed class MemberResDTO permits MemberSpecResDTO {
    private final String name;
    private final UserRole role;

    private final String profileUrl;

    public MemberResDTO(Member member) {
        this.name = member.getMemberName();
        this.role = member.getRole();

        this.profileUrl = member.getImg().getUrl();
    }
}
