package com.plitsoft.ojt.member.service;

import com.plitsoft.ojt.global.DatabaseCleanupCallback;
import com.plitsoft.ojt.global.exception.DuplicateValueException;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.domain.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DatabaseCleanupCallback.class)
@SpringBootTest
@Transactional
class memberServiceTest {

    @Autowired
    public EntityManager em;

    @Autowired
    public memberService memberService;

    @Test
    @DisplayName("memberService: 회원가입")
    void memberServiceJoinTest() {
        // given
        Member member = memberServiceTestSupport.createTestMember("test");

        // when
        Long joinedUserId = memberService.join(member);
        em.flush();

        Member searchedMember = memberService.find(joinedUserId);

        // then
        assertEquals(member, searchedMember);
    }

    @Test
    @DisplayName("memberService: 회원가입 - 이메일 중복 방지")
    void memberServiceJoinTest2() {
        // given
        Member member1 = memberServiceTestSupport.createTestMember("test");
        Member member2 = memberServiceTestSupport.createTestMember("test");

        // when
        memberService.join(member1);

        // then
        assertThrows(DuplicateValueException.class, () -> {
            memberService.join(member2);
        });
    }

    @Test
    @DisplayName("memberService: 회원조회(다건) - 이름")
    void memberServiceGetManyByNameTest() {
        // given
        Member member1 = memberServiceTestSupport.createTestMember("test");
        Member member2 = memberServiceTestSupport.createTestMember("test2");
        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> memberList = memberService.findByName("test");

        // then
        assertEquals(memberList.size(), 2);
    }

    @Test
    @DisplayName("memberService: 회원조회(다건) - 이메일")
    void memberServiceGetManyByEmailTest() {
        // given
        Member member1 = memberServiceTestSupport.createTestMember("test");
        Member member2 = memberServiceTestSupport.createTestMember("test2");
        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> memberListTest = memberService.findByEmail("test");
        List<Member> memberListTest2 = memberService.findByEmail("test2@test.com");

        // then
        assertEquals(memberListTest.size(), 2);
        assertEquals(memberListTest2.size(), 1);
    }

}

class memberServiceTestSupport {

    static Member createTestMember(String name) {
        return createTestMember(name, UserRole.USER);
    }

    static Member createTestMember(String name, UserRole role) {
        return Member.builder()
                .email(String.format("%s@test.com", name))
                .memberName(String.format("%sUser", name))
                .role(role)
                .build();
    }
}