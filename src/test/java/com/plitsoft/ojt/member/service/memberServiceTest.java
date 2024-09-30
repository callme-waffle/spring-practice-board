package com.plitsoft.ojt.member.service;

import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.domain.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class memberServiceTest {

    @Autowired
    public memberService memberService;

    @Test
    @DisplayName("userService: 회원가입")
    void userServiceJoinTest() {
        // given
        Member member = Member.builder()
                .email( "test@test.com" )
                .memberName( "testUser" )
                .role( UserRole.USER )
                .build();

        // when
        Long joinedUserId = memberService.join(member);
        Member searchedMember = memberService.find( joinedUserId );

        // then
        assertEquals(member, searchedMember);
    }

    @DisplayName("userService: 회원조회(단건)")
    void userServiceGetOneTest() {
        // given

        // when

        // then
    }

    @DisplayName("userService: 회원조회(다건) - 이름")
    void userServiceGetManyByNameTest() {
        // given

        // when

        // then
    }

    @DisplayName("userService: 회원조회(다건) - 이메일")
    void userServiceGetManyByEmailTest() {
        // given

        // when

        // then
    }

    @DisplayName("userService: 중복체크 - 이메일")
    void userServiceDupCheckEmailTest() {
        // given

        // when

        // then
    }

}