package com.plitsoft.ojt.auth.dto.request;

public record PostMemberJoinReqQueryDTO(
        String name,
        String email,
        String part,
        String description,
        String role
) {}
