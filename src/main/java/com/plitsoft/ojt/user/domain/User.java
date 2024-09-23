package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.file.domain.FileData;
import com.plitsoft.ojt.global.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String userName;

    @OneToOne(mappedBy = "id")
    private Pw password;

    @ManyToOne()
    private Part part;

    @OneToOne(mappedBy = "id")
    private FileData img;

    private String desc;

    private UserRole role;
}
