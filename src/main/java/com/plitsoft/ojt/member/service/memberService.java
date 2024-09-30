package com.plitsoft.ojt.member.service;

import com.plitsoft.ojt.global.exception.DuplicateValueException;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.repository.memberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Member find(Long user_id) {
        return memberRepository.find( user_id );
    }

    public List<Member> findByName(String user_name) {
        return memberRepository.findByName( user_name );
    }

    public List<Member> findByEmail(String user_email) {
        return memberRepository.findByEmail( user_email );
    }

    private boolean checkEmailDup(String user_email) {
        if ( !memberRepository.findByEmail( user_email ).isEmpty() )
            throw new DuplicateValueException( "Email Already In Use" );
        return true;
    }
}
