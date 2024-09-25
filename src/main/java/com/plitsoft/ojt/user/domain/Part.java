package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Part extends CommonDAO {
    @Id @GeneratedValue
    @Column(name = "part_id")
    private Long id;

    private String partName;
}
