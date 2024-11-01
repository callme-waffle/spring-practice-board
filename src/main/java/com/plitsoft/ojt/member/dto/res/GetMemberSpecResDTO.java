 package com.plitsoft.ojt.member.dto.res;

import com.plitsoft.ojt.member.domain.Member;
import lombok.Getter;

@Getter
public final class GetMemberSpecResDTO extends GetMemberResDTO {

    private final String email;
    private final String desc;
    private final String partName;

    public GetMemberSpecResDTO(Member member) {
        super(member);
        this.email = member.getEmail();
        this.desc = member.getDesc();
        this.partName = member.getPart().getPartName();
    }
}
