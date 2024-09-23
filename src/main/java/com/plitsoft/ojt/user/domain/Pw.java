package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Pw extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "pw_save_id")
    private Long id;

    @OneToOne(mappedBy = "password")
    private User user;

    private String salt;

    private String pw;

    public Pw() {}

    public Pw(Long id, User user, String salt, String pw) {
        this.id = id;
        this.user = user;
        this.salt = salt;
        this.pw = pw;
    }
}
