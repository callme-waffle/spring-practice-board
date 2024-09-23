package com.plitsoft.ojt.user.domain;

import com.plitsoft.ojt.file.domain.FileData;
import com.plitsoft.ojt.global.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class User extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pw_id")
    private Pw password;

    @OneToMany(
            mappedBy = "id",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    private List<Pw> pw_history;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id")
    private Part part;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "img_id")
    private FileData img;

    private String desc;

    private UserRole role;
}
