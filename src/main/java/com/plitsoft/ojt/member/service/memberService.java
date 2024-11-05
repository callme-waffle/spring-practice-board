package com.plitsoft.ojt.member.service;

import com.plitsoft.ojt.global.exception.DuplicateValueException;
import com.plitsoft.ojt.member.domain.Member;
import com.plitsoft.ojt.member.dao.FindRepositoryDAO;

import com.plitsoft.ojt.member.dto.request.GetMembersReqQueryDTO;
import com.plitsoft.ojt.member.dto.request.PatchMembersReqQueryDTO;
import com.plitsoft.ojt.member.map.MemberMapper;
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
        checkEmailDup(member.getEmail());
        return memberRepository.save(member);
    }

    public Member find(Long memberId) {
        return memberRepository.find(memberId);
    }

    public List<Member> findByName(String memberName) {
        return memberRepository.findByName(memberName);
    }

    public List<Member> findByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }

    public List<Member> findByFilter(Map<MemberFilter, String> filter) {
        return memberRepository.findAll( filter );
    }

    private boolean checkEmailDup(String member_email) {
        if (!memberRepository.findByEmail(member_email).isEmpty())
            throw new DuplicateValueException("Email Already In Use");
        return true;
    }
}
