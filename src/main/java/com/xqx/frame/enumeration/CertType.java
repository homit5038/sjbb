package com.xqx.frame.enumeration;

import org.springframework.util.ObjectUtils;

import com.xqx.frame.converter.base.ConvertedEnum;
import com.xqx.frame.converter.base.ConvertedEnumResolver;



/**
 * 证件类型枚举
 */
public enum CertType implements ConvertedEnum<Integer> {
    ID_CARD(1, "ID_CARD", "身份证"),
    ID_CARD_HK(2, "ID_CARD_HK", "港澳台身份证"),
    PASSPORT(3, "PASSPORT", "护照"),
    FAMILY_REGISTER(4, "FAMILY_REGISTER", "户口簿"),
    OFFICER(5, "OFFICER", "军官证(士兵证)"),
    ORG_CODE(6, "ORG_CODE", "组织机构代码"),
    BUSINESS_LICENSE(7, "BUSINESS_LICENSE", "营业执照"),
    OTHER(8, "OTHER", "其他");

    //code入库
    private Integer code;
    private String name;
    private String displayName;

    CertType(Integer code, String name, String displayName) {
        this.code = code;
        this.name = name;
        this.displayName = displayName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public Integer toDbValue() {
        return this.code;
    }

    // static resolving:
    public static final ConvertedEnumResolver<CertType, Integer> resolver = new ConvertedEnumResolver<>(CertType.class);

    public static CertType fromDbValue(Integer dbValue) {
        if (ObjectUtils.isEmpty(dbValue)) {
            return null;
        }
        return resolver.get(dbValue);
    }
}
