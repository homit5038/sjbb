package com.xqx.frame.model;

import javax.persistence.*;

@Entity
@Table(name = "SYS_SEQUENCE")
@NamedStoredProcedureQuery(name = "getSystemSeq", procedureName = "getSystemSeq", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "sysNo", type = String.class)})
public class SysSequence extends BaseAuditEntity {

    /**
     *
     */
    private static final long serialVersionUID = 3160500359970386261L;

    /**
     * 名称
     */

    @Column(name = "name_")
    private String name;
    /**
     * 前缀
     */
    @Column(name = "prefix_")
    private String prefix;

    /**
     * 流水号位数
     */
    @Column(name = "length_")
    private String length;

    /**
     * 流水号
     */
    @Column(name = "sequence_")
    private String sequence;

    /**
     * 日期
     */
    @Column(name = "datestr_")
    private String dateStr;
    /**
     * 描述
     */
    @Column(name = "memo")
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
