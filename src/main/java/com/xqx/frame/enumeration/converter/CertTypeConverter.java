package com.xqx.frame.enumeration.converter;

import com.xqx.frame.converter.base.EnumAttributeConverter;
import com.xqx.frame.enumeration.CertType;

public class CertTypeConverter extends EnumAttributeConverter<CertType, Integer>{
    @Override
    public CertType convertToEntityAttribute(Integer dbData) {
        return CertType.fromDbValue(dbData);
    }
}
