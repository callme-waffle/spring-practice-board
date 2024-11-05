package com.plitsoft.ojt.member.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class GetMembersReqQueryDTO {

    private final String name;

    @Email
    private final String email;

    private final String part;

}
