package com.pasindu.librarymanager.util;

import com.pasindu.librarymanager.enumeration.RoleEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, String> {

    @Override
    public String convertToDatabaseColumn(RoleEnum role) {
        return role == null ? null : role.name();
    }

    @Override
    public RoleEnum convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RoleEnum.valueOf(dbData);
    }
}

