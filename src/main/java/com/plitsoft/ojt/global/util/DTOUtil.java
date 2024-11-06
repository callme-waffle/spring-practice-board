package com.plitsoft.ojt.global.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DTOUtil {

    /**
     * DTO 객체 내에서 null이 아닌 필드를 조회하는 메서드명만 모아 리스트형식으로 반환합니다
     *
     * @param dto 메서드명을 가져올 DTO객체
     * @param <T> DTO 객체
     * @return 메서드명 리스트
     */
    public static <T> List<String> getNotNullValueGetterNames(T dto) {
        return getGetterMethodNames(dto).stream()
                .filter(getterName -> checkIsReturnNull(dto, getterName))
                .toList();
    }

    /**
     * DTO 객체의 메서드명을 모아 가져옵니다
     *
     * @param dto 메서드명을 가져올 DTO객체
     * @param <T> DTO 객체
     * @return 메서드명 리스트
     */
    private static <T> List<String> getGetterMethodNames(T dto) {
        Method[] classMethods = dto.getClass().getMethods();
        return Arrays.stream(classMethods)
                .map(Method::getName)
                .toList();
    }

    /**
     * 지정 메서드를 가져옵니다
     *
     * @param dto        메서드를 가지고 있는 DTO Instance
     * @param methodName DTO Class에 포함되어있는 method의 이름
     * @param <T>        DTO 객체
     * @return 메서드 객체
     */
    public static <T> Method getMethod(T dto, String methodName) {
        try {
            return dto.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * 지정 메서드를 실행합니다
     *
     * @param dto        메서드를 가지고 있는 DTO Instance
     * @param methodName DTO Class에 포함되어있는 method의 이름
     * @param args       메서드 실행 시 필요한 인자
     * @param <T>        DTO 객체
     * @param <R>        반환자료형 (=메서드 실행결과의 자료형)
     * @return 메서드 실행결과
     */
    public static <T, R> R runMethod(T dto, String methodName, Object... args) {
        try {
            Method method = getMethod(dto, methodName);
            return (R) method.invoke(dto, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    /**
     * 제공된 메서드의 응답이 null인지 확인하여 반환합니다.
     *
     * @param dto        메서드를 가지고 있는 DTO Instance
     * @param methodName DTO Class에 포함되어있는 method의 이름
     * @param <T>        DTO 객체
     * @return 메서드 실행결과의 null 여부
     */
    private static <T> boolean checkIsReturnNull(T dto, String methodName) {
        Object v = runMethod(dto, methodName);
        return v == null;
    }

    /**
     * getter 메서드 이름에서 필드명을 추롢해 반환합니다
     * @param getterName 추론에 사용할 getter메서드 이름
     * @return 추론된 필드명
     */
    public static String getFieldNameByGetterName(String getterName) {
        String removedPrefix = getterName.substring(3);
        String lowerFirstChar = removedPrefix.substring(0, 1).toLowerCase();
        return lowerFirstChar + removedPrefix.substring(1);
    }
}
