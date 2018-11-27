package com.pivaiot.starter.project.common.db;

import com.pivaiot.common.db.jpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SnowflakeIdEntity<T> extends BaseEntity<T> {
    @Id
    @Column
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    protected Long generateId() {
        //Todo snowflake
        return 1L;
    }
}
