package com.plitsoft.ojt.global.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommonDTO {

    private LocalDateTime registeredAt;

    private LocalDateTime modifiedAt;

    public CommonDTO() {}

    public CommonDTO(LocalDateTime registeredAt, LocalDateTime modifiedAt) {
        this.registeredAt = registeredAt;
        this.modifiedAt = modifiedAt;
    }
}
