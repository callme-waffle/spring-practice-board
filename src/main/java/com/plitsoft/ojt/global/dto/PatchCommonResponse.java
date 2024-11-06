package com.plitsoft.ojt.global.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatchCommonResponse<T> {

    private final boolean isSucceed;

    private final int acknowledged;

    private final int found;

    private final T updated;
}
