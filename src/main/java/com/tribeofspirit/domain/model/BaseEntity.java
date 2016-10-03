package com.tribeofspirit.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tribeofspirit.domain.model.attribute.EntityStatus;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Lewis Wang
 * Date: 7/6/11
 * Time: 9:39 AM
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {

    protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    protected Long id = new Long("-1");

    private Date createTime;

    private Date modifyTime;

    private String creator;

    private String modifier;

    private EntityStatus entityStatus = EntityStatus.NORMAL;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(length = 100)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(length = 100)
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    @Transient
    public boolean isIdentified() {
        return id == null || id == -1L;
    }

    @JsonIgnore
    @Transient
    public boolean isPersist() {
        return id != null && id != -1L;
    }

    @Transient
    public abstract Long getId();

    @Transient
    public String getLocation() {
        return getClass().getSimpleName();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
