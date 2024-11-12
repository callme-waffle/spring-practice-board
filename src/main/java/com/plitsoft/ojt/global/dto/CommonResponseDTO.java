package com.plitsoft.ojt.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommonResponseDTO<T> {

    public record VersionResponseDTO(
            String apiVersion,
            String clientVersion
    ) {}

    @RequiredArgsConstructor
    @Getter
    public static class ResultResponseDTO<U> {
        private final boolean isSucceed;
        private final int statusCode;
        private final U result;
    }

    private final String apiVersion;

    private final String clientVersion;

    private final boolean isSucceed;

    private final int statusCode;

    private final T result;

    public CommonResponseDTO(VersionResponseDTO versions, ResultResponseDTO<T> results) {
        this.apiVersion = versions.apiVersion();
        this.clientVersion = versions.clientVersion();

        this.isSucceed = results.isSucceed();
        this.statusCode = results.getStatusCode();
        this.result = results.getResult();
    }
}
