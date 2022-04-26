package com.ysh.spring.mnm.common.util.file;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import com.ysh.spring.mnm.common.code.Config;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class FileInfo {


    @Id
    @GeneratedValue
    private Long flIdx;
    private String type;
    private Long typeIdx;
    private String originFileName;
    private String renameFileName;
    private String savePath;
    @Column(columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @Column(columnDefinition = "integer default 0")
    private Boolean isDel;

    public String getDownloadURL() {
        return Config.UPLOAD_PATH.DESC + savePath + renameFileName;
    }

    public String getLink() {
        return Config.DOMAIN.DESC + "/file/" + savePath + renameFileName;
    }
}
