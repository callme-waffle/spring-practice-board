package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.*;

@Entity
public class Pw extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "user_pw_id")
    private Long id;

    @OneToOne( mappedBy = "id")
    private User user;

    private String pw;
}
