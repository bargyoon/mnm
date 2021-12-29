package com.ysh.spring.mnm.common.util.file;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private String originFileName;
	private String renameFileName;
	private String savePath;
	@Column(columnDefinition = "date default sysdate")
	private LocalDate regDate;
	@Column(columnDefinition = "number default 0")
	private Boolean isDel;

	public String getDownloadURL() {
		return Config.UPLOAD_PATH.DESC + savePath + renameFileName;
	}

	public String getLink() {
		return Config.DOMAIN.DESC + "/file/" + savePath + renameFileName;
	}
}
