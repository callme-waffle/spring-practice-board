package com.plitsoft.ojt.global.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
public class CommonDAO {
    @CreatedDate
    private LocalDateTime registeredAt;

    @Setter
    private LocalDateTime modifiedAt;

    public CommonDAO() {}

    public CommonDAO(LocalDateTime registeredAt, LocalDateTime modifiedAt) {
        this.registeredAt = registeredAt;
        this.modifiedAt = modifiedAt;
    }
}
