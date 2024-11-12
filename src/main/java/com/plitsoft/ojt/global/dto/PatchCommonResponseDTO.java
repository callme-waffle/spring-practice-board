package com.plitsoft.ojt.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PatchCommonResponseDTO<T> extends CommonResponseDTO {

    @RequiredArgsConstructor
    @Getter
    public static class PatchResultContentDTO<U> {
        private final int acknowledged;
        private final int found;
        private final U updated;
    }

    private final int acknowledged;

    private final int found;

    private final T updated;

    public PatchCommonResponseDTO(VersionResponseDTO versions, PatchResultContentDTO<T> resultContent) {
        this(versions, 200, resultContent);
    }

    public PatchCommonResponseDTO(
            VersionResponseDTO versions,
            int statusCode,
            PatchResultContentDTO<T> resultContent
    ) {
        super(versions, new ResultResponseDTO(true, statusCode, resultContent));

        this.acknowledged = resultContent.getAcknowledged();
        this.found = resultContent.getFound();
        this.updated = resultContent.getUpdated();
    }
}
