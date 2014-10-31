package com.cse308.projectaim.hibernate.types;

import com.cse308.projectaim.beans.AIMFileWrapperBean;
import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "AIM_ENTITY_FILE_WRAPPER")
public class AIMFileWrapper implements Serializable, AIMEntity {

    private Integer id;
    private String fileName;
    private byte[] bytes;

    public AIMFileWrapper() {
    }

    public AIMFileWrapper(AIMFileWrapperBean aimFileWrapperBean) {
        id = aimFileWrapperBean.getId();
        fileName = aimFileWrapperBean.getFileName();
        bytes = aimFileWrapperBean.getBytes();
    }

    @Id
    @Column(name = "FILE_ID")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "FILE_DATA", length = 1000000)
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AIMFileWrapper other = (AIMFileWrapper) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.fileName == null) ? (other.fileName != null) : !this.fileName.equals(other.fileName)) {
            return false;
        }
        if (!Arrays.equals(this.bytes, other.bytes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AIMFileWrapper";
    }

    @Override
    public Serializable primaryKey() {
        return this.id;
    }
}
