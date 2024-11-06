package com.plitsoft.ojt.member.map;

import com.plitsoft.ojt.global.map.EntityMapper;
import com.plitsoft.ojt.global.map.StaticEntityMapper;
import lombok.Getter;

import java.util.Locale;

@Getter
public enum MemberMapper implements EntityMapper {
    name("memberName"),
    email("email"),
    part("part");

    private final String fieldName;

    MemberMapper(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getGetterName() {
        return StaticEntityMapper.getMethodName(this.fieldName, StaticEntityMapper.METHOD_TYPE.GETTER);
    }

    @Override
    public String getSetterName() {
        return StaticEntityMapper.getMethodName(this.fieldName, StaticEntityMapper.METHOD_TYPE.SETTER);
    }
}
