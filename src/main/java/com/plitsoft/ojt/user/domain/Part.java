package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Part extends CommonDAO {
    @Id @GeneratedValue
    @Column(name = "part_id")
    private Long id;

    private String partName;
}
