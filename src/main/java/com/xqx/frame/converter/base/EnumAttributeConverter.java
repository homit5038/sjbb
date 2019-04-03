package com.xqx.frame.converter.base;


import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;

/**
 * Base implementation for converting enums stored in DB.
 * Enums must implement {@link ConvertedEnum}.
 */
public abstract class EnumAttributeConverter<X extends ConvertedEnum<Y>, Y>
        implements AttributeConverter<X, Y>
{
    @Override
    public final Y convertToDatabaseColumn(X x) {
        if(ObjectUtils.isEmpty(x)){
            return null;
        }
        return x.toDbValue();
    }
}
