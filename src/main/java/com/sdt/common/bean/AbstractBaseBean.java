package com.sdt.eims.base.bean;

import com.sdt.common.listener.EimsBaseBeanAuditListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 定义实体中的公共字段，并进行赋值（抽象类）
 * <p>
 * 目前，包含的公共字段为：id, uuid, createTime, updateTime, deleteTime, delete
 * <p>
 * 如果需要创建人、修改人等信息时，可在此定义，
 * 然后在{@link EimsBaseBeanAuditListener com.sdt.eims.base.bean.auditlistener.EimsBaseBeanAuditListener}中,
 * 结合具体的Session逻辑进行实现创建人、修改人等信息的赋值
 *
 * @author lianghaoran
 */
@MappedSuperclass
@EntityListeners(EimsBaseBeanAuditListener.class)
public abstract class AbstractBaseBean implements Serializable {

    private static final long serialVersionUID = 8234901230503025132L;

    /**
     * 主键ID, 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 逻辑主键 UUID
     */
    @Column(name = "uuid", length = 36, nullable = false)
    private String uuid;

    /**
     * 创建时间
     */
    @Column(name = "create_time", length = 19, nullable = false)
    private String createTime;

    /**
     * 最后一次修改时间
     */
    @Column(name = "update_time", length = 19)
    private String updateTime;

    /**
     * 删除时间
     */
    @Column(name = "delete_time", length = 19)
    private String deleteTime;

    /**
     * 是否已删除（逻辑删除标志位）
     */
    @Column(name = "deleted", length = 1, nullable = false)
    private Boolean deleted;

    /**
     * 扩展字段
     * (方便以后进行扩展)
     */
    @Column(name= "extend1", length = 50)
    private String extend1;

    @XmlTransient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @XmlTransient
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @XmlTransient
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @XmlTransient
    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    @XmlTransient
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }
}
