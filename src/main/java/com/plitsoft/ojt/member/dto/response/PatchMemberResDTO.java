package com.plitsoft.ojt.member.dto.response;

import com.plitsoft.ojt.global.dto.PatchCommonResponse;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.common.UpdateServiceDTO;

public final class PatchMemberResDTO extends PatchCommonResponse<Member> {
    public PatchMemberResDTO(
            int acknowlegedCount,
            int foundCount, Member updated
    ) {
        super(true,
                acknowlegedCount, foundCount,
                updated
        );
    }

    public PatchMemberResDTO(UpdateServiceDTO updateResult) {
        super(true,
                updateResult.acknowledgedCount(), updateResult.foundCount(),
                updateResult.member().get(0)
        );
    }
}