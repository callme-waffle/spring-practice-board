package com.plitsoft.ojt.member.dto.response;

import com.plitsoft.ojt.global.dto.PatchCommonResponseDTO;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.common.UpdateServiceDTO;

public final class PatchMemberResDTO extends PatchCommonResponseDTO<Member> {
    public PatchMemberResDTO(
            VersionResponseDTO versions,
            int acknowlegedCount,
            int foundCount, Member updated
    ) {
        super(
                versions,
                new PatchResultContentDTO<>(acknowlegedCount, foundCount, updated)
        );
    }

    public PatchMemberResDTO(VersionResponseDTO versions, UpdateServiceDTO updateResult) {
        super(
            versions,
            new PatchResultContentDTO<>(
                updateResult.acknowledgedCount(),
                updateResult.foundCount(),
                updateResult.member().get(0)
            )
        );
    }
}