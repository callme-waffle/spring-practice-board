package com.plitsoft.ojt.file.domain;

import com.plitsoft.ojt.global.domain.CommonDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FileData extends CommonDAO {
    @Id @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String orgName;

    private String saveName;

    public FileData() {}

    public FileData(Long id, String orgName, String saveName) {
        this.id = id;
        this.orgName = orgName;
        this.saveName = saveName;
    }
}
