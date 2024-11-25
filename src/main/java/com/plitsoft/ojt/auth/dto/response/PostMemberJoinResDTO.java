package com.plitsoft.ojt.auth.dto.response;

import com.plitsoft.ojt.global.dto.CommonResponseDTO;

public class PostMemberJoinResDTO<T> extends CommonResponseDTO<T> {
    public PostMemberJoinResDTO(VersionResponseDTO versions, T resultContent) {
        super(versions, new ResultResponseDTO<T>(
                true, 200,
                resultContent
        ));
    }

    public PostMemberJoinResDTO(VersionResponseDTO versions, int statusCode, T resultContent) {
        super(versions, new ResultResponseDTO<T>(
                true,
                statusCode,
                resultContent
        ));
    }
}
