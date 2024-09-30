package com.plitsoft.ojt.member.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Pw extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "pw_save_id")
    private Long id;

    @OneToOne(mappedBy = "password")
    private Member member;

    private String salt;

    private String pw;
}
