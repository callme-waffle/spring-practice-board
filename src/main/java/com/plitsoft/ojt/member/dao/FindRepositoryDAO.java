package com.plitsoft.ojt.member.dao;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class FindRepositoryDAO {

    private final String memberName;

    @Email
    private final String email;

    private final String part;

}
