package com.plitsoft.ojt.global.map;

public class StaticEntityMapper {
    public enum METHOD_TYPE {
        GETTER("get"),
        SETTER("set");

        private final String prefix;

        METHOD_TYPE(String prefix) { this.prefix = prefix; }
    }

    public static String getMethodName(String fieldName, METHOD_TYPE methodType) {
        String methodPrefix = methodType.prefix;
        String methodUpperPoint = fieldName.substring(0, 1).toUpperCase();
        String remainMethodName = fieldName.substring(1);
        return String.format("%s%s%s", methodPrefix, methodUpperPoint, remainMethodName);
    }
}
