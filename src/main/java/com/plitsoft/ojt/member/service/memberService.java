package com.plitsoft.ojt.member.service;

import com.plitsoft.ojt.global.exception.DuplicateValueException;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dto.common.MemberFilter;
import com.plitsoft.ojt.member.repository.memberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class memberService {

    private final memberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        checkEmailDup( member.getEmail() );
        return memberRepository.save(member);
    }

    public Member find(Long member_id) {
        return memberRepository.find( member_id );
    }

    public List<Member> findByName(String member_name) {
        return memberRepository.findByName( member_name );
    }

    public List<Member> findByEmail(String member_email) {
        return memberRepository.findByEmail( member_email );
    }

    public List<Member> findByFilter(Map<MemberFilter, String> filter) {
        return memberRepository.findAll( filter );
    }

    private boolean checkEmailDup(String member_email) {
        if ( !memberRepository.findByEmail( member_email ).isEmpty() )
            throw new DuplicateValueException( "Email Already In Use" );
        return true;
    }
}
