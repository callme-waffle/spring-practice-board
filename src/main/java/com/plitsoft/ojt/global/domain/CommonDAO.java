package com.plitsoft.ojt.global.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommonDAO {
    private LocalDateTime registeredAt;

    @Setter
    private LocalDateTime modifiedAt;
}
