package com.plitsoft.ojt.member.domain;

import com.plitsoft.ojt.file.domain.FileData;
import com.plitsoft.ojt.global.domain.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Member extends CommonDAO {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Email @NotNull
    private String email;

    @NotBlank
    private String memberName;

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

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;
}
